#!/usr/bin/env python
# encoding: utf-8

from ops.base.Instruction import NoOperandsInstruction


class POP(NoOperandsInstruction):
    """
    bottom -> top
    [...][c][b][a]
                |
                V
    [...][c][b]
    """

    def execute(self, frame):
        stack = frame.operand_stack
        stack.pop_slot()


# 由于实现中采用的是python的无类型数，不管是double还是int都占用一个操作数栈位置
class POP2(NoOperandsInstruction):
    def execute(self, frame):
        stack = frame.operand_stack
        stack.pop_slot()
