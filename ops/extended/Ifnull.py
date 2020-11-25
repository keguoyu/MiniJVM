#!/usr/bin/env python
# encoding: utf-8

from ops.base.BranchLogic import branch
from ops.base.Instruction import BranchInstruction


class IFNULL(BranchInstruction):
    def execute(self, frame):
        ref = frame.operand_stack.pop_ref()
        if ref is None:
            branch(frame, self.offset)


class IFNONNULL(BranchInstruction):
    def execute(self, frame):
        ref = frame.operand_stack.pop_ref()
        if ref:
            branch(frame, self.offset)
