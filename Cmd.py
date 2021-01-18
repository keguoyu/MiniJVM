#!/usr/bin/env python
# encoding: utf-8
# 命令行工具 使用方式：python3 Main.py [-h] [-v VERSION] [-d DEBUG] [-cp CLASS_PATH] [-cn CLASS_NAME] [-arg ARGS] [-jre JREOPTION]
import argparse


class Cmd:
    version = False
    debug = False
    class_path = ""
    class_name = ""
    args = []
    jreOption = ""

    def __init__(self, arglist):
        self.input_args = arglist
        self.parser = argparse.ArgumentParser()
        self.parser.add_argument('-v', '--version', help='show the version info')
        self.parser.add_argument('-d', '--debug', help='show the debug info when jvm runs')
        self.parser.add_argument('-cp', '--class_path', help='define the destination of class file')
        self.parser.add_argument('-cn', '--class_name', help='the name of target class file')
        self.parser.add_argument('-arg', '--args', help='the args pass to the class')
        self.parser.add_argument('-jre', '--jreOption', help='the path where jre is located')
        self.parse()

    def parse(self):
        args = self.parser.parse_args()
        if args:
            self.version = args.version or False
            self.debug = args.debug or False
            self.class_path = args.class_path or ''
            self.class_name = args.class_name or ''
            self.args = args.args or []
            self.jreOption = args.jreOption

    @staticmethod
    def print_version():
        print("MiniJVM 1.0 written by python")

    @staticmethod
    def print_help():
        print('usage: Main.py [-h] [-v] [-d] [-cp CLASS_PATH] [-cn CLASS_NAME] [-arg ARGS] [-jre JREOPTION]')

    def print_classpath(self):
        print("classpath:{0} class:{1} args:{2}\n".format(self.cpOption, self.class_name, self.print_args()))

    # 打印传入参数
    def print_args(self):
        return '[' + ' '.join(self.args) + ']'
