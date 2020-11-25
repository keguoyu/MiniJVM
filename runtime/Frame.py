#!/usr/bin/env python
# encoding: utf-8

from runtime.LocalVars import LocalVars
from runtime.OperandStack import OperandStack
from runtime.Thread import Thread
from runtime.heap.Method import Method


class Frame:
    def __init__(self, thread: Thread, method: Method):
        self.lower = None
        self.thread = thread
        self.method = method
        self.local_vars = LocalVars(method.max_locals)
        self.operand_stack = OperandStack(method.max_stack)
        self.next_pc = 0

    def revert_next_pc(self):
        self.next_pc = self.thread.pc
