#!/usr/bin/env python
# encoding: utf-8

from ops.base import ClassInitLogic
from ops.base.Instruction import Index16Instruction
from runtime.Frame import Frame


class GET_STATIC(Index16Instruction):
    def execute(self, frame: Frame):
        cp = frame.method.get_class().constant_pool
        field_ref = cp.get_constant(self.index)
        field = field_ref.resolve_field()
        clazz = field.get_class()

        if not clazz.init_started:
            frame.revert_next_pc()
            ClassInitLogic.init_class(frame.thread, clazz)
            return

        # 如果解析后的字段不是静态字段，抛出IncompatibleClassChangeError异常
        if not field.is_static():
            raise RuntimeError("java.lang.IncompatibleClassChangeError")

        descriptor = field.descriptor
        slot_id = field.slot_id
        slots = clazz.static_vars
        stack = frame.operand_stack

        if descriptor[0] in {"Z", "B", "C", "S", "I", "J"}:
            stack.push_numeric(slots.get_numeric(slot_id))
        elif descriptor[0] == 'F':
            stack.push_float(slots.get_float(slot_id))
        elif descriptor[0] == 'D':
            stack.push_double(slots.get_double(slot_id))
        elif descriptor[0] in {"L", "["}:
            stack.push_ref(slots.get_ref(slot_id))
