#!/usr/bin/env python
# encoding: utf-8
# MiniJVM的入口文件
import os
import sys

# 用于从命令行启动
curPath = os.path.abspath(os.path.dirname(__file__))
rootPath = os.path.split(curPath)[0]
sys.path.append(rootPath)

from Argv import Argv
from vm.JVM import JavaVirtualMachine


def main():
    argv = Argv(sys.argv)
    if argv.version:
        Argv.print_version()
    else:
        JavaVirtualMachine(argv).start()    


if __name__ == '__main__':
    main()
