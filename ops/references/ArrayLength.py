#!/usr/bin/env python
# encoding: utf-8

from ops.base.Instruction import NoOperandsInstruction
from vm.StackFrame import StackFrame


class ARRAY_LENGTH(NoOperandsInstruction):
    def execute(self, frame: StackFrame):
        stack = frame.operand_stack
        arr_ref = stack.pop_ref()
        # 如果数组引用是null，则抛出NullPointerException异常
        if arr_ref is None:
            raise RuntimeError("java.lang.NullPointerException")

        # 否则获得数组长度，推入操作数栈
        arr_len = arr_ref.array_length()
        stack.push_numeric(arr_len)
