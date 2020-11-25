#!/usr/bin/env python
# encoding: utf-8"

import os

from classloader.CompositeEntry import CompositeEntry
from classloader.ZipEntry import ZipEntry


class WildcardEntry(CompositeEntry):

    def __init__(self, path):
        super().__init__(path)
        # 移除路径末尾的'*'符号
        base_dir = path[:-1]
        # 遍历base_dir创建zipEntry
        for root, dirs, files in os.walk(base_dir):
            for name in files:
                if name.endswith(".jar") or name.endswith(".JAR"):
                    jar_entry = ZipEntry(os.path.join(root, name))
                    self.compositeEntryList.append(jar_entry)
