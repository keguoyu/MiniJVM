#!/usr/bin/env python
# encoding: utf-8

import os
from lang.classloader.BootClassLoader import BootClassLoader
from lang.classloader.BootExtClassLoader import BootExtClassLoader
from lang.classloader.UserClassLoader import UserClassLoader


# 构建双亲委派模型
def new_parent_delegate_class_loader(jre_option, class_path):
    boot_class_loader = BootClassLoader(jre_option)

    ext_class_loader = BootExtClassLoader(jre_option)
    ext_class_loader.parent = boot_class_loader

    user_class_loader = UserClassLoader(class_path)
    user_class_loader.parent = ext_class_loader

    return user_class_loader


def exists(path):
    if os.path.isdir(path):
        return True
    return False
