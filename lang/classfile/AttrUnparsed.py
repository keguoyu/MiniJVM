#!/usr/bin/env python
# encoding: utf-8

from lang.classfile.AttributeInfo import AttributeInfo


class UnparsedAttribute(AttributeInfo):
    def __init__(self, attr_name, attr_len):
        self.name = attr_name
        self.length = attr_len
        self.info = []

    def read_info(self, class_reader):
        self.info = class_reader.read_bytes(self.length)
