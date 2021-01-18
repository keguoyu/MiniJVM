#!/usr/bin/env python
# encoding: utf-8

from ops.base.Instruction import NoOperandsInstruction
from runtime import Frame


def _add(frame: Frame):
    stack = frame.operand_stack
    v1 = stack.pop_numeric()
    v2 = stack.pop_numeric()
    result = v1 + v2
    stack.push_numeric(result)


# double add
class DADD(NoOperandsInstruction):
    def execute(self, frame):
        stack = frame.operand_stack
        v1 = stack.pop_double()
        v2 = stack.pop_double()
        result = v1 + v2
        stack.push_double(result)


# float add
class FADD(NoOperandsInstruction):
    def execute(self, frame):
        stack = frame.operand_stack
        v1 = stack.pop_float()
        v2 = stack.pop_float()
        result = v1 + v2
        stack.push_float(result)


# int add
class IADD(NoOperandsInstruction):
    def execute(self, frame):
        _add(frame)


# long add
class LADD(NoOperandsInstruction):
    def execute(self, frame):
        _add(frame)
