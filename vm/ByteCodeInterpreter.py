#!/usr/bin/env python
# encoding: utf-8
# 字节码执行工具
from vm.JvmThread import JvmThread
from vm.runtime.Method import Method
from vm.runtime.StringConstantPool import j_string


def interpret(method: Method, log_inst: bool, args):
    thread = JvmThread()
    frame = thread.new_frame(method)
    thread.push_frame(frame)

    j_args = create_args_array(method.get_class().loader, args)
    frame.local_vars.set_ref(0, j_args)

    try:
        loop(thread, log_inst)
    except RuntimeError as e:
        log_frames(thread)
        print("LocalVars: {0}".format(frame.local_vars))
        print("OperandStack: {0}".format(frame.operand_stack))
        print(e)


def loop(thread, log_inst):
    from ops.base.BytecodeReader import BytecodeReader
    from ops.Factory import Factory

    reader = BytecodeReader()

    while True:
        frame = thread.current_frame
        pc = frame.next_pc
        thread.pc = pc

        reader.reset(frame.method.code, pc)
        op_code = reader.read_uint8()
        inst = Factory.new_instruction(op_code)
        inst.fetch_operands(reader)
        frame.next_pc = reader.pc

        if log_inst:
            log_instruction(frame, inst)

        inst.execute(frame)

        if thread.is_stack_empty():
            break


def log_frames(thread):
    while not thread.is_stack_empty():
        frame = thread.pop_frame()
        method = frame.method
        class_name = method.get_class().name
        print(">> pc: {0:4} {1}.{2}{3}".format(frame.next_pc, class_name, method.name, method.descriptor))


def log_instruction(frame, inst):
    method = frame.method
    class_name = method.get_class().name
    method_name = method.name
    pc = frame.thread.pc
    print("{0}.{1}() #{2:<2} {3} {4} operand_stack: {5} local_vars: {6}".format(class_name, method_name, pc,
                                                                                inst.__class__.__name__,
                                                                                print_obj(inst),
                                                                                frame.operand_stack,
                                                                                frame.local_vars))


def create_args_array(loader, args):
    string_class = loader.load_class("java/lang/String")
    args_array = string_class.array_class().new_array(len(args))
    j_args = args_array.refs()
    for i, arg in enumerate(args):
        j_args[i] = j_string(loader, arg)
    return args_array


def print_obj(obj):
    return ' '.join(['%s:%s' % item for item in obj.__dict__.items()])
