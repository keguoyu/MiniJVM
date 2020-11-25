#!/usr/bin/env python
# encoding: utf-8

from ops.base import ClassInitLogic
from ops.base.Instruction import Index16Instruction


class PUT_STATIC(Index16Instruction):
    def execute(self, frame):
        # 先获得当前方法、当前类和当前常量池
        current_method = frame.method
        current_class = current_method.get_class()
        cp = current_class.constant_pool
        # 解析字段符号引用
        field_ref = cp.get_constant(self.index)
        field = field_ref.resolve_field()
        clazz = field.get_class()

        if not clazz.init_started:
            frame.revert_next_pc()
            ClassInitLogic.init_class(frame.thread, clazz)
            return

        # 如果解析后的字段是实例字段而非静态字段，则抛出IncompatibleClassChangeError异常
        if not field.is_static():
            raise RuntimeError("java.lang.IncompatibleClassChangeError")
        # 如果是final字段，则实际操作的是静态变量，只能在类初始化方法中给它赋值，否则抛出IllegalAccessError异常
        # 类初始化方法由编译器生成，名字是<clinit>
        if field.is_final():
            if current_class != clazz or current_method.name != "<clinit>":
                raise RuntimeError("java.lang.IllegalAccessError")

        descriptor = field.descriptor
        slot_id = field.slot_id
        slots = clazz.static_vars
        stack = frame.operand_stack

        if descriptor[0] in {"Z", "B", "C", "S", "I", "J"}:
            slots.set_numeric(slot_id, stack.pop_numeric())
        elif descriptor[0] == 'D':
            slots.set_double(slot_id, stack.pop_double())
        elif descriptor[0] == 'F':
            slots.set_float(slot_id, stack.pop_float())
        elif descriptor[0] in {"L", "["}:
            slots.set_ref(slot_id, stack.pop_ref())
