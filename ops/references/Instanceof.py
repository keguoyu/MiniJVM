#!/usr/bin/env python
# encoding: utf-8

from ops.base.Instruction import Index16Instruction
from vm.StackFrame import StackFrame


class INSTANCE_OF(Index16Instruction):
    def execute(self, frame: StackFrame):
        stack = frame.operand_stack
        # 弹出对象引用
        ref = stack.pop_ref()

        # 如果是null，则把0推入操作数栈。
        if ref is None:
            stack.push_numeric(0)
            return

        cp = frame.method.get_class().constant_pool
        class_ref = cp.get_constant(self.index)
        clazz = class_ref.resolved_class()
        if ref.is_instance_of(clazz):
            stack.push_numeric(1)
        else:
            stack.push_numeric(0)
