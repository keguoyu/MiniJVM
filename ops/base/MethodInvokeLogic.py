#!/usr/bin/env python
# encoding: utf-8

from vm.StackFrame import StackFrame
from vm.runtime.Method import Method


def invoke_method(invoker_frame: StackFrame, method: Method):
    # 创建新的帧并推入Java虚拟机栈
    thread = invoker_frame.thread
    new_frame = thread.new_frame(method)
    thread.push_frame(new_frame)

    # 确定方法的参数在局部变量表中占用多少位置
    arg_slot_slot = method.arg_slot_count
    if arg_slot_slot > 0:
        for i in range(arg_slot_slot - 1, -1, -1):
            slot = invoker_frame.operand_stack.pop_slot()
            new_frame.local_vars.set_slot(i, slot)
