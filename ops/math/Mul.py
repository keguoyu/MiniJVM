#!/usr/bin/env python
# encoding: utf-8

from ops.base.Instruction import NoOperandsInstruction


def _mul(frame):
    stack = frame.operand_stack
    v2 = stack.pop_numeric()
    v1 = stack.pop_numeric()
    result = v1 * v2
    stack.push_numeric(result)


# double mul
class DMUL(NoOperandsInstruction):
    def execute(self, frame):
        stack = frame.operand_stack
        v2 = stack.pop_double()
        v1 = stack.pop_double()
        result = v1 * v2
        stack.push_double(result)


# float mul
class FMUL(NoOperandsInstruction):
    def execute(self, frame):
        stack = frame.operand_stack
        v2 = stack.pop_float()
        v1 = stack.pop_float()
        result = v1 * v2
        stack.push_float(result)


# int mul
class IMUL(NoOperandsInstruction):
    def execute(self, frame):
        _mul(frame)


# long mul
class LMUL(NoOperandsInstruction):
    def execute(self, frame):
        _mul(frame)
