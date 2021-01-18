#!/usr/bin/env python
# encoding: utf-8

from ops.base import MethodInvokeLogic, ClassInitLogic
from ops.base.Instruction import Index16Instruction


class INVOKE_STATIC(Index16Instruction):
    def execute(self, frame):
        cp = frame.method.get_class().constant_pool
        method_ref = cp.get_constant(self.index)
        resolved_method = method_ref.resolved_method()
        if not resolved_method.is_static():
            raise RuntimeError("java.lang.IncompatibleClassChangeError")

        clazz = resolved_method.get_class()
        if not clazz.init_started:
            frame.revert_next_pc()
            ClassInitLogic.init_class(frame.thread, clazz)
            return

        MethodInvokeLogic.invoke_method(frame, resolved_method)
