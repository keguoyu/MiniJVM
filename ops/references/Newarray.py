#!/usr/bin/env python
# encoding: utf-8

from ops.base.Instruction import Instruction
from vm.StackFrame import StackFrame


class NEW_ARRAY(Instruction):
    AT_BOOLEAN = 4
    AT_CHAR = 5
    AT_FLOAT = 6
    AT_DOUBLE = 7
    AT_BYTE = 8
    AT_SHORT = 9
    AT_INT = 10
    AT_LONG = 11

    def __init__(self):
        self.atype = 0

    def fetch_operands(self, reader):
        self.atype = reader.read_uint8()

    def execute(self, frame: StackFrame):

        stack = frame.operand_stack
        count = stack.pop_numeric()
        # 如果count小于0，则抛出NegativeArraySizeException异常
        if count < 0:
            raise RuntimeError("java.lang.NegativeArraySizeException")

        # 根据atype值使用当前类的类加载器加载数组类，然后创建数组对象并推入操作数栈。
        class_loader = frame.method.get_class().loader
        arr_class = self.get_primitive_array_class(class_loader, self.atype)
        arr = arr_class.new_array(int(count))
        stack.push_ref(arr)

    @staticmethod
    def get_primitive_array_class(loader, atype):
        if atype == NEW_ARRAY.AT_BOOLEAN:
            return loader.load_class("[Z")
        elif atype == NEW_ARRAY.AT_BYTE:
            return loader.load_class("[B")
        elif atype == NEW_ARRAY.AT_CHAR:
            return loader.load_class("[C")
        elif atype == NEW_ARRAY.AT_SHORT:
            return loader.load_class("[S")
        elif atype == NEW_ARRAY.AT_INT:
            return loader.load_class("[I")
        elif atype == NEW_ARRAY.AT_LONG:
            return loader.load_class("[J")
        elif atype == NEW_ARRAY.AT_FLOAT:
            return loader.load_class("[F")
        elif atype == NEW_ARRAY.AT_DOUBLE:
            return loader.load_class("[D")
        else:
            raise RuntimeError("Invalid atype!")
