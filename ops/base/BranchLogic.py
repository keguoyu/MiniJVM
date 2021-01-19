#!/usr/bin/env python
# encoding: utf-8

from vm import StackFrame


def branch(frame: StackFrame, offset):
    pc = frame.thread.pc
    next_pc = pc + offset
    frame.next_pc = next_pc
