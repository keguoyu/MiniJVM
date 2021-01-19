#!/usr/bin/env python
# encoding: utf-8

from native.Registry import register
from vm.StackFrame import StackFrame
from vm.runtime import StringConstantPool


def intern(frame: StackFrame):
    this = frame.local_vars.get_this()
    interned = StringConstantPool.intern_string(this)
    frame.operand_stack.push_ref(interned)


register("java/lang/String", "intern",
         "()Ljava/lang/String;", intern)
