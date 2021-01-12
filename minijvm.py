import sys

from args.arguments import Arguments
from vm.virtual_machine import VirtualMachine


def start():
    args = Arguments(sys.argv)
    if not args.right_fmt or args.help_flag:
        print("Usage: python3 minijvm.py [-classpath classpath] class")
    elif args.version_flag:
        print("MiniJVM by python version 1.0")
    else:
        VirtualMachine(args.class_path, args.class_name).start()


if __name__ == '__main__':
    start()
