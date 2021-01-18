#!/usr/bin/env python
# encoding: utf-8

from ops.base.Instruction import NoOperandsInstruction


def _dcmp(frame, gFlag):
    stack = frame.operand_stack
    v2 = stack.pop_double()
    v1 = stack.pop_double()
    if v1 > v2:
        stack.push_numeric(1)
    elif v1 == v2:
        stack.push_numeric(0)
    elif v1 < v2:
        stack.push_numeric(-1)
    elif gFlag:
        stack.push_numeric(1)
    else:
        stack.push_numeric(-1)


class DCMPG(NoOperandsInstruction):
    def execute(self, frame):
        _dcmp(frame, True)


class DCMPL(NoOperandsInstruction):
    def execute(self, frame):
        _dcmp(frame, False)
