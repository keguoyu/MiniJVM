#!/usr/bin/env python
# encoding: utf-8

from ops.base.Instruction import Index16Instruction
from vm.StackFrame import StackFrame


class PUT_FIELD(Index16Instruction):
    def execute(self, frame: StackFrame):
        current_method = frame.method
        current_class = current_method.get_class()
        cp = current_class.constant_pool
        field_ref = cp.get_constant(self.index)
        field = field_ref.resolve_field()

        # 解析后的字段必须是实例字段，否则抛出IncompatibleClassChangeError异常。
        if field.is_static():
            raise RuntimeError("java.lang.IncompatibleClassChangeError")
        # 如果是final字段，则只能在构造函数中初始化，否则抛出IllegalAccessError异常。
        if field.is_final():
            if current_class != field.get_class() or current_method.name != "<init>":
                raise RuntimeError("java.lang.IllegalAccessError")

        descriptor = field.descriptor
        slot_id = field.slot_id
        stack = frame.operand_stack

        if descriptor[0] in {"Z", "B", "C", "S", "I", "J"}:
            val = stack.pop_numeric()
            ref = stack.pop_ref()
            if ref is None:
                raise RuntimeError("java.lang.NollPointerException")
            ref.fields().set_numeric(slot_id, val)
        if descriptor[0] == 'D':
            val = stack.pop_double()
            ref = stack.pop_ref()
            if ref is None:
                raise RuntimeError("java.lang.NollPointerException")
            ref.fields().set_double(slot_id, val)
        if descriptor[0] == 'F':
            val = stack.pop_float()
            ref = stack.pop_ref()
            if ref is None:
                raise RuntimeError("java.lang.NollPointerException")
            ref.fields().set_float(slot_id, val)
        elif descriptor[0] in {"L", "["}:
            val = stack.pop_ref()
            ref = stack.pop_ref()
            if ref is None:
                raise RuntimeError("java.lang.NollPointerException")
            ref.fields().set_ref(slot_id, val)
