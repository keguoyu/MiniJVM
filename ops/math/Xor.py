#!/usr/bin/env python
# encoding: utf-8

from ops.base.Instruction import NoOperandsInstruction
from runtime import Frame


def _xor(frame: Frame):
    stack = frame.operand_stack
    v2 = stack.pop_numeric()
    v1 = stack.pop_numeric()
    result = v1 ^ v2
    stack.push_numeric(result)


# int xor
class IXOR(NoOperandsInstruction):
    def execute(self, frame):
        _xor(frame)


# long xor
class LXOR(NoOperandsInstruction):
    def execute(self, frame):
        _xor(frame)
