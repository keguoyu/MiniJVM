#!/usr/bin/env python
# encoding: utf-8

from ops.base.Instruction import NoOperandsInstruction


class NOP(NoOperandsInstruction):
    def execute(self, frame):
        # 什么也不用做
        pass
