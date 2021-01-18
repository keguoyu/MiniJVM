#!/usr/bin/env python
# encoding: utf-8

from ops.base.Instruction import NoOperandsInstruction
from runtime import Frame


def _or(frame: Frame):
    stack = frame.operand_stack
    v2 = stack.pop_numeric()
    v1 = stack.pop_numeric()
    result = v1 | v2
    stack.push_numeric(result)


# int or
class IOR(NoOperandsInstruction):
    def execute(self, frame):
        _or(frame)


# long or
class LOR(NoOperandsInstruction):
    def execute(self, frame):
        _or(frame)
