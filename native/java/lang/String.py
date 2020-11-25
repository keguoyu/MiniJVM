#!/usr/bin/env python
# encoding: utf-8

from native.Registry import register
from runtime.Frame import Frame
from runtime.heap import StringPool


def intern(frame: Frame):
    this = frame.local_vars.get_this()
    interned = StringPool.intern_string(this)
    frame.operand_stack.push_ref(interned)


register("java/lang/String", "intern",
         "()Ljava/lang/String;", intern)
