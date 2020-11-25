#!/usr/bin/env python
# encoding: utf-8

from ops.base.BranchLogic import branch
from ops.base.Instruction import BranchInstruction


class GOTO(BranchInstruction):
    def execute(self, frame):
        branch(frame, self.offset)
