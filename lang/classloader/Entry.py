#!/usr/bin/env python
# encoding: utf-8

from abc import ABCMeta, abstractmethod


class Entry(metaclass=ABCMeta):
    # 路径分隔符
    path_list_separator = ";"

    # 寻找和加载class文件，接口方法
    @abstractmethod
    def read_class(self, class_name):
        pass

    # 根据参数常见不同类型的Entry实例
    @staticmethod
    def new_entry(path):
        from lang.classloader.CompositeEntry import CompositeEntry
        from lang.classloader.WildcardEntry import WildcardEntry
        from lang.classloader.ZipEntry import ZipEntry
        from lang.classloader.DirEntry import DirEntry

        if Entry.path_list_separator in path:
            return CompositeEntry(path)
        elif path.endswith("*"):
            return WildcardEntry(path)
        elif path.endswith(".jar") or path.endswith(".JAR") or path.endswith(".zip") or path.endswith(".ZIP"):
            return ZipEntry(path)
        else:
            return DirEntry(path)
