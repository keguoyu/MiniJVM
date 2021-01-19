#!/usr/bin/env python
# encoding: utf-8

from ops.base.Instruction import Index16Instruction
from vm.StackFrame import StackFrame


class CHECK_CAST(Index16Instruction):
    def execute(self, frame: StackFrame):
        stack = frame.operand_stack
        ref = stack.pop_ref()
        stack.push_ref(ref)
        if ref is None:
            return

        cp = frame.method.get_class().constant_pool
        class_ref = cp.get_constant(self.index)
        clazz = class_ref.resolved_class()
        if not ref.is_instance_of(clazz):
            raise RuntimeError("java.lang.ClassCastException")
