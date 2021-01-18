#!/usr/bin/env python
# encoding: utf-8

from ops.base.Instruction import Index16Instruction
from runtime.Frame import Frame


class GET_FIELD(Index16Instruction):
    def execute(self, frame: Frame):
        cp = frame.method.get_class().constant_pool
        field_ref = cp.get_constant(self.index)
        field = field_ref.resolve_field()

        if field.is_static():
            raise RuntimeError("java.lang.IncompatibleClassChangeError")

        stack = frame.operand_stack
        ref = stack.pop_ref()
        if ref is None:
            raise RuntimeError("java.lang.NollPointerException")

        descriptor = field.descriptor
        slot_id = field.slot_id
        slots = ref.fields()

        if descriptor[0] in {"Z", "B", "C", "S", "I", "J"}:
            stack.push_numeric(slots.get_numeric(slot_id))
        elif descriptor[0] == 'F':
            stack.push_float(slots.get_float(slot_id))
        elif descriptor[0] == 'D':
            stack.push_double(slots.get_double(slot_id))
        elif descriptor[0] in {"L", "["}:
            stack.push_ref(slots.get_ref(slot_id))
