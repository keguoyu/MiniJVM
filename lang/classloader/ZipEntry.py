#!/usr/bin/env python
# encoding: utf-8

import os.path
import zipfile

from lang.classloader.Entry import Entry


class ZipEntry(Entry):
    def __init__(self, path):
        self.absPath = os.path.abspath(path)

    # 从ZIP文件中提取class文件，返回16进制格式的数据
    def read_class(self, class_name):
        error, data = None, None
        with zipfile.ZipFile(self.absPath, 'r') as z:
            for file in z.filelist:
                if file.filename == class_name:
                    try:
                        data = z.open(file, 'r').read()
                    except Exception as e:
                        error = e
                        return None, None, error
                    break
        if not data:
            error = "class not found:{0}".format(class_name)
            return None, None, error
        return data, self, error

    def __str__(self):
        return self.absPath
