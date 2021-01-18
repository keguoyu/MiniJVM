#!/usr/bin/env python
# encoding: utf-8

import ctypes

from ops.base.BranchLogic import branch
from ops.base.Instruction import NoOperandsInstruction


class GOTO_W(NoOperandsInstruction):
    def __init__(self):
        self.offset = 0

    def fetch_operands(self, reader):
        self.offset = ctypes.c_int(reader.read_int32()).value

    def execute(self, frame):
        branch(frame, self.offset)
