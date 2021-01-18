#!/usr/bin/env python
# encoding: utf-8

import ctypes

from ops.base.Instruction import NoOperandsInstruction


# Convert long to double
class L2D(NoOperandsInstruction):
    def execute(self, frame):
        stack = frame.operand_stack
        l = stack.pop_numeric()
        d = float(l)
        stack.push_double(d)


# Convert long to float
class L2F(NoOperandsInstruction):
    def execute(self, frame):
        stack = frame.operand_stack
        l = stack.pop_numeric()
        f = float(l)
        stack.push_float(f)


# Convert long to int
class L2I(NoOperandsInstruction):
    def execute(self, frame):
        stack = frame.operand_stack
        l = stack.pop_numeric()
        i = ctypes.c_int32(l).value
        stack.push_numeric(i)
