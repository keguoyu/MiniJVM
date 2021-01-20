#!/usr/bin/env python 
# -*- coding: utf-8 -*-

from abc import abstractmethod, ABCMeta


class JvmClassLoader(metaclass=ABCMeta):
    parent = None

    def __init__(self, path):
        super().__init__()
        self.entry = self.parse_entry(path)

    @abstractmethod
    def parse_entry(self, path):
        pass

    # 双亲委派模型 
    # 去加载一个类的时候先尝试去用父类进行加载
    def read_class(self, class_name):
        if not class_name.endswith('.class'):
            class_name = class_name + '.class'
        # parent不为空 先尝试让parent去加载
        if self.parent is not None:
            data, entry, error = self.parent.read_class(class_name)
            if data:
                return data, entry, error
            else:
                return self.entry.read_class(class_name)
        return self.entry.read_class(class_name)
