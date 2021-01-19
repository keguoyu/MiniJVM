#!/usr/bin/env python
# encoding: utf-8

from native.Registry import register
from vm.StackFrame import StackFrame


# public final native Class<?> getClass();
def get_class(frame: StackFrame):
    """

    :param frame:
    :return:
    """
    # 从局部变量表中拿到this引用
    this = frame.local_vars.get_this()
    # 有了this引用，通过get_class()方法拿到它的Class对象，通过j_class属性拿到类对象
    clazz = this.get_class().j_class
    # 把类对象推入操作数栈顶
    frame.operand_stack.push_ref(clazz)


def hash_code(frame: StackFrame):
    this = frame.local_vars.get_this()
    hash_value = hash(this)
    frame.operand_stack.push_numeric(hash_value)


def clone(frame: StackFrame):
    this = frame.local_vars.get_this()
    cloneable = this.get_class().loader.load_class("java/lang/Cloneable")
    if not this.get_class().is_implements(cloneable):
        raise RuntimeError("java.lang.CloneNotSupportedException")

    frame.operand_stack.push_ref(this.clone())


jlObject = 'java/lang/Object'
register(jlObject, 'getClass', '()Ljava/lang/Class;', get_class)
register(jlObject, "hashCode", "()I", hash_code)
register(jlObject, "clone", "()Ljava/lang/Object;", clone)
