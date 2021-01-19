#!/usr/bin/env python
# encoding: utf-8

import os

from lang.classloader.Entry import Entry


class DirEntry(Entry):

    # 构造函数
    def __init__(self, path):
        # 将参数转换成绝对路径
        self.absDir = os.path.abspath(path)

    def read_class(self, class_name):
        file_name = os.path.join(self.absDir, class_name)
        data, error = None, None
        try:
            data = open(file_name, "rb").read()
        except IOError as e:
            error = e
        return data, self, error

    def __str__(self):
        return self.absDir
