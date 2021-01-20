#!/usr/bin/env python
# encoding: utf-8
# 命令行工具 使用方式：python3 Main.py [-h] [-v VERSION] [-d DEBUG] [-cp CLASS_PATH] [-cn CLASS_NAME] [-arg ARGS] [-jre JREOPTION]
import argparse
import os


class Argv:
    version = False
    debug = False
    class_path = ""
    class_name = ""
    args = []
    jreOption = ""

    def __init__(self, arg_list):
        self.input_args = arg_list
        self.parser = argparse.ArgumentParser()
        self.parser.add_argument('-v', '--version', help='版本信息')
        self.parser.add_argument('-d', '--debug', help='开启debug模式，将虚拟机执行的日志信息输出')
        self.parser.add_argument('-cp', '--class_path', help='要加载的class文件的路径')
        self.parser.add_argument('-cn', '--class_name', help='要加载的class文件的名称(不需要添加.class后缀)')
        self.parser.add_argument('-arg', '--args', help='要执行的class文件传入的命令行参数')
        self.parser.add_argument('-jre', '--jreOption', help='系统jre的安装路径 可不传')
        self.parse()

    def parse(self):
        args = self.parser.parse_args()
        if args:
            self.version = args.version or False
            self.debug = args.debug or False
            # 未指定class_path 那么则查找和main.py同一文件夹下的class
            self.class_path = args.class_path or os.path.abspath(os.path.dirname(__file__))
            self.class_name = args.class_name or ''
            self.args = args.args or []
            self.jreOption = args.jreOption

    @staticmethod
    def print_version():
        print("MiniJVM 1.0 written by python")
