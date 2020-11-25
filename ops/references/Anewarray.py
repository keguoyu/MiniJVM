#!/usr/bin/env python
# encoding: utf-8

from ops.base.Instruction import Index16Instruction


class ANEW_ARRAY(Index16Instruction):
    def execute(self, frame):
        import ctypes

        cp = frame.method.get_class().constant_pool
        class_ref = cp.get_constant(self.index)
        component_class = class_ref.resolved_class()

        stack = frame.operand_stack
        count = stack.pop_numeric()
        if count < 0:
            raise RuntimeError("java.lang.NegativeArraySizeException")

        arr_class = component_class.array_class()
        arr = arr_class.new_array(ctypes.c_uint(count).value)
        stack.push_ref(arr)
