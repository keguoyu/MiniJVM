#!/usr/bin/env python
# encoding: utf-8

from ops.base.Instruction import NoOperandsInstruction


class SWAP(NoOperandsInstruction):
    """
    bottom -> top
    [...][c][b][a]
              \/
              /\
             V  V
    [...][c][a][b]
    """

    def execute(self, frame):
        stack = frame.operand_stack
        slot1 = stack.pop_slot()
        slot2 = stack.pop_slot()
        stack.push_slot(slot1)
        stack.push_slot(slot2)
