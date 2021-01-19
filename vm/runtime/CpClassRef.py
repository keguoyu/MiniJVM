#!/usr/bin/env python
# encoding: utf-8
"""
@author: HuRuiFeng
@file: CpClassRef.py
@time: 2019/9/16 17:57
@desc: 类符号引用
"""
from lang.classfile.ConstantClassInfo import ConstantClassInfo
from vm.runtime.CpSymRef import SymRef


class ClassRef(SymRef):

    def __init__(self, constant_pool, class_info: ConstantClassInfo):
        super().__init__()
        self.cp = constant_pool
        self.class_name = class_info.name
