#!/usr/bin/env python 
# -*- coding: utf-8 -*-
from lang.classloader import Loaders
from lang.classloader.JvmClassLoader import JvmClassLoader
import os


class JreClassLoader(JvmClassLoader):
    def parse_entry(self, path):
        jre = self.get_jre(path)
        return self.new_entry(jre)

    def new_entry(self, jre):
        pass

    # 获取jre的路径
    # 在Mac上好像这获取通常有一些有问题 简易直接用命令行指定
    def get_jre(self, jreOption):
        if jreOption and Loaders.exists(jreOption):
            return jreOption
        if Loaders.exists("./jre"):
            return "./jre"
        jh = os.environ.get("JAVA_HOME")
        if jh:
            return os.path.join(jh, "jre")
        raise RuntimeError("Can not find jre folder!")
