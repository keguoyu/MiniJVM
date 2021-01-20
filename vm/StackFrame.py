#!/usr/bin/env python
# encoding: utf-8
# 栈桢 
# 定义：随着方法的调用而创建，随着方法结束而销毁，无论是正常结束还是抛出异常导致程序结束都算作
# 方法结束。栈桢存储在虚拟机栈结构中。每一个栈桢都有自己的局部变量表和操作数栈。大小在
# 编译期间就已经确定了。
# 方法调用：在一个方法中调用另一个方法时，一个新的栈桢会被创建，当前栈桢把程序控制权交到新的栈桢，
# 当新的方法执行完成，则把结果返回给前一个栈桢，虚拟机使前一个栈桢成为当前栈桢。

from vm.LocalVariables import LocalVariables
from vm.OperandStack import OperandStack
from vm.JvmThread import JvmThread
from vm.runtime.Method import Method


class StackFrame:
    def __init__(self, thread: JvmThread, method: Method):
        # 下一个栈桢 当在一个方法中调用一个新的方法的时候
        # 一个新的栈桢会被创建
        self.next = None
        # 所属线程
        self.thread = thread
        # 栈桢执行的方法
        self.method = method
        # 局部变量表
        self.local_vars = LocalVariables(method.max_locals)
        # 操作数栈
        self.operand_stack = OperandStack(method.max_stack)
        self.next_pc = 0

    def revert_next_pc(self):
        self.next_pc = self.thread.pc
