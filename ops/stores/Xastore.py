#!/usr/bin/env python
# encoding: utf-8


import ctypes

from ops.base.Instruction import NoOperandsInstruction
from vm.StackFrame import StackFrame


def check_not_none(ref):
    if ref is None:
        raise RuntimeError("java.lang.NullPointerException")


def check_index(arr_len, index):
    if index < 0 or index >= int(arr_len):
        raise RuntimeError("ArrayIndexOutOfBoundsException")


class AASTORE(NoOperandsInstruction):
    def execute(self, frame: StackFrame):
        stack = frame.operand_stack
        ref = stack.pop_ref()
        index = stack.pop_numeric()
        arr_ref = stack.pop_ref()

        check_not_none(arr_ref)
        ref_array = arr_ref.refs()
        check_index(len(ref_array), index)
        ref_array[index] = ref


class BASTORE(NoOperandsInstruction):
    def execute(self, frame: StackFrame):
        stack = frame.operand_stack
        val = stack.pop_numeric()
        index = stack.pop_numeric()
        arr_ref = stack.pop_ref()

        check_not_none(arr_ref)
        byte_array = arr_ref.bytes()
        check_index(len(byte_array), index)
        byte_array[index] = ctypes.c_int8(val).value


class CASTORE(NoOperandsInstruction):
    def execute(self, frame: StackFrame):
        stack = frame.operand_stack
        val = stack.pop_numeric()
        index = int(stack.pop_numeric())
        arr_ref = stack.pop_ref()

        check_not_none(arr_ref)
        char_array = arr_ref.chars()
        check_index(len(char_array), index)
        char_array[index] = ctypes.c_uint16(val).value


class DASTORE(NoOperandsInstruction):
    def execute(self, frame: StackFrame):
        stack = frame.operand_stack
        val = stack.pop_double()
        index = int(stack.pop_numeric())
        arr_ref = stack.pop_ref()

        check_not_none(arr_ref)
        double_array = arr_ref.doubles()
        check_index(len(double_array), index)
        double_array[index] = ctypes.c_float(val).value


class FASTORE(NoOperandsInstruction):
    def execute(self, frame: StackFrame):
        stack = frame.operand_stack
        val = stack.pop_float()
        index = stack.pop_numeric()
        arr_ref = stack.pop_ref()

        check_not_none(arr_ref)
        float_array = arr_ref.floats()
        check_index(len(float_array), index)
        float_array[index] = ctypes.c_float(val).value


class IASTORE(NoOperandsInstruction):
    def execute(self, frame: StackFrame):
        stack = frame.operand_stack
        val = stack.pop_numeric()
        index = stack.pop_numeric()
        arr_ref = stack.pop_ref()

        check_not_none(arr_ref)
        int_array = arr_ref.ints()
        check_index(len(int_array), index)
        int_array[index] = ctypes.c_int32(val).value


class LASTORE(NoOperandsInstruction):
    def execute(self, frame: StackFrame):
        stack = frame.operand_stack
        val = stack.pop_numeric()
        index = stack.pop_numeric()
        arr_ref = stack.pop_ref()

        check_not_none(arr_ref)
        long_array = arr_ref.longs()
        check_index(len(long_array), index)
        long_array[index] = ctypes.c_int64(val).value


class SASTORE(NoOperandsInstruction):
    def execute(self, frame: StackFrame):
        stack = frame.operand_stack
        val = stack.pop_numeric()
        index = stack.pop_numeric()
        arr_ref = stack.pop_ref()

        check_not_none(arr_ref)
        short_array = arr_ref.shorts()
        check_index(len(short_array), index)
        short_array[index] = ctypes.c_int16(val).value
