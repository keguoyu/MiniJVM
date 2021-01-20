#!/usr/bin/env python
# encoding: utf-8
# 拓展类加载器 加载的路径是 jdk安装路径/jre/lib/ext/下的jar中的class

from lang.classloader.JreClassLoader import JreClassLoader
from lang.classloader.WildcardEntry import WildcardEntry
import os


class BootExtClassLoader(JreClassLoader):

    def new_entry(self, jre):
        jre_ext_path = os.path.join(jre, "lib", "ext", "*")
        return WildcardEntry(jre_ext_path)
