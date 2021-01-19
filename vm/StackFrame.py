#!/usr/bin/env python
# encoding: utf-8

from vm.LocalVariables import LocalVariables
from vm.OperandStack import OperandStack
from vm.JvmThread import JvmThread
from vm.runtime.Method import Method


class StackFrame:
    def __init__(self, thread: JvmThread, method: Method):
        self.lower = None
        self.thread = thread
        self.method = method
        self.local_vars = LocalVariables(method.max_locals)
        self.operand_stack = OperandStack(method.max_stack)
        self.next_pc = 0

    def revert_next_pc(self):
        self.next_pc = self.thread.pc
