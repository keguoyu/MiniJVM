#!/usr/bin/env python
# encoding: utf-8

from ops.base.BranchLogic import branch
from ops.base.Instruction import BranchInstruction


# ifeq: x == 0
class IFEQ(BranchInstruction):
    def execute(self, frame):
        val = frame.operand_stack.pop_numeric()
        if val == 0:
            branch(frame, self.offset)


# ifne: x != 0
class IFNE(BranchInstruction):
    def execute(self, frame):
        val = frame.operand_stack.pop_numeric()
        if val != 0:
            branch(frame, self.offset)


# iflt: x < 0
class IFLT(BranchInstruction):
    def execute(self, frame):
        val = frame.operand_stack.pop_numeric()
        if val < 0:
            branch(frame, self.offset)


# ifle: x <= 0
class IFLE(BranchInstruction):
    def execute(self, frame):
        val = frame.operand_stack.pop_numeric()
        if val <= 0:
            branch(frame, self.offset)


# ifgt: x > 0
class IFGT(BranchInstruction):
    def execute(self, frame):
        val = frame.operand_stack.pop_numeric()
        if val > 0:
            branch(frame, self.offset)


# ifge: x >= 0
class IFGE(BranchInstruction):
    def execute(self, frame):
        val = frame.operand_stack.pop_numeric()
        if val >= 0:
            branch(frame, self.offset)
