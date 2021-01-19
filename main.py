#!/usr/bin/env python
# encoding: utf-8
# MiniJVM的入口文件
import os
import sys

# 用于从命令行启动
curPath = os.path.abspath(os.path.dirname(__file__))
rootPath = os.path.split(curPath)[0]
sys.path.append(rootPath)

from args.Argv import Argv
from vm.ByteCodeInterpreter import ByteCodeInterpreter
from lang.classloader.Classpath import Classpath
from vm.runtime.ClassLoader import ClassLoader


def main():
    argv = Argv(sys.argv)
    if argv.version:
        Argv.print_version()
    else:
        start_jvm(argv)


def start_jvm(cmd):
    class_path = Classpath.parse(cmd.jreOption, cmd.class_path)

    class_loader = ClassLoader(class_path, cmd.debug)

    if cmd.class_name is None or cmd.class_name == '':
        raise RuntimeError('class_name is not valid')
    else:
        class_name = cmd.class_name.replace(".", "/")
        main_class = class_loader.load_class(class_name)
        main_method = main_class.get_main_method()

        if main_method:
            ByteCodeInterpreter.interpret(main_method, cmd.debug, cmd.args)
        else:
            print("Main method not found in class {0}".format(cmd.class_name))


if __name__ == '__main__':
    main()
