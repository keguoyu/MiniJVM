#!/usr/bin/env python
# encoding: utf-8

from ops.base.BranchLogic import branch
from ops.base.Instruction import BranchInstruction
from vm import StackFrame


def _icmpPop(frame: StackFrame):
    stack = frame.operand_stack
    val2 = stack.pop_numeric()
    val1 = stack.pop_numeric()
    return val1, val2


# if_icmpeq: x1 == x2
class IF_ICMPEQ(BranchInstruction):
    def execute(self, frame):
        val1, val2 = _icmpPop(frame)
        if val1 == val2:
            branch(frame, self.offset)


# if_icmpne: x1 != x2
class IF_ICMPNE(BranchInstruction):
    def execute(self, frame):
        val1, val2 = _icmpPop(frame)
        if val1 != val2:
            branch(frame, self.offset)


# if_icmplt: x1 < x2
class IF_ICMPLT(BranchInstruction):
    def execute(self, frame):
        val1, val2 = _icmpPop(frame)
        if val1 < val2:
            branch(frame, self.offset)


# if_icmple: x1 <= x2
class IF_ICMPLE(BranchInstruction):
    def execute(self, frame):
        val1, val2 = _icmpPop(frame)
        if val1 <= val2:
            branch(frame, self.offset)


# if_icmpgt: x1 > x2
class IF_ICMPGT(BranchInstruction):
    def execute(self, frame):
        val1, val2 = _icmpPop(frame)
        if val1 > val2:
            branch(frame, self.offset)


# if_icmpge: x1 >= x2
class IF_ICMPGE(BranchInstruction):
    def execute(self, frame):
        val1, val2 = _icmpPop(frame)
        if val1 >= val2:
            branch(frame, self.offset)
