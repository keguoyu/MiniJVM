#!/usr/bin/env python
# encoding: utf-8

from lang.classfile.ConstantInfo import ConstantInfo


class ConstantClassInfo(ConstantInfo):
    def __init__(self, constant_pool):
        from lang.classfile.ConstantPool import ConstantPool
        self.cp = ConstantPool(constant_pool)
        self.name_index = 0

    def read_info(self, class_reader):
        self.name_index = int.from_bytes(class_reader.read_unit16(), byteorder="big")

    @property
    def name(self):
        return self.cp.get_utf8(self.name_index)
