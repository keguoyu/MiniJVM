#!/usr/bin/env python
# encoding: utf-8

from ops.base.BranchLogic import branch
from ops.base.Instruction import BranchInstruction


def _acmpPop(frame):
    stack = frame.operand_stack
    val2 = stack.pop_ref()
    val1 = stack.pop_ref()
    return val1, val2


class IF_ACMPEQ(BranchInstruction):
    def execute(self, frame):
        val1, val2 = _acmpPop(frame)
        if val1 is val2:
            branch(frame, self.offset)


class IF_ACMPNE(BranchInstruction):
    def execute(self, frame):
        val1, val2 = _acmpPop(frame)
        if val1 is not val2:
            branch(frame, self.offset)
