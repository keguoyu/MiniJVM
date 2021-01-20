#!/usr/bin/env python
# encoding: utf-8
# 启动类加载器 加载的路径是 jdk安装路径/jre/lib/下的jar中的class

from lang.classloader.JreClassLoader import JreClassLoader
import os
from lang.classloader.WildcardEntry import WildcardEntry


class BootClassLoader(JreClassLoader):
    def new_entry(self, jre):
        jre_lib_path = os.path.join(jre, "lib", "*")
        return WildcardEntry(jre_lib_path)
