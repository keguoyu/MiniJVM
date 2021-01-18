#!/usr/bin/env python
# encoding: utf-8

from ops.base.Instruction import NoOperandsInstruction


class LCMP(NoOperandsInstruction):
    def execute(self, frame):
        stack = frame.operand_stack
        v2 = stack.pop_numeric()
        v1 = stack.pop_numeric()
        if v1 > v2:
            stack.push_numeric(1)
        elif v1 == v2:
            stack.push_numeric(0)
        else:
            stack.push_numeric(-1)
