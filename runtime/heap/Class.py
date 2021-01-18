#!/usr/bin/env python
# encoding: utf-8

from classfile.ClassFile import ClassFile
from runtime.Slot import Slots
from runtime.heap import AccessFlags, ClassNameHelper
from runtime.heap.ClassNameHelper import PrimitiveTypes
from runtime.heap.ConstantPool import ConstantPool
from runtime.heap.Field import Field
from runtime.heap.Method import Method


# 源文件名在ClassFile的属性表中，提取源文件名信息
def get_source_file(classFile: ClassFile):
    source_file_attr = classFile.source_file_attribute()
    if source_file_attr is not None:
        return source_file_attr.file_name

    return "Unknown"


class Class:
    def __init__(self):
        # 访问标志
        self.access_flags = 0
        # 类名（完全限定名），具有java/lang/Object的形式
        self.name = ""
        # 超类名（完全限定名）
        self.super_class_name = ""
        # 接口名（完全限定名）
        self.interface_names = []
        # 运行时常量池指针
        self.constant_pool = None
        # 字段表
        self.fields = []
        # 方法表
        self.methods = []
        # 源文件名
        self.source_file = ""
        # 加载器
        self.loader = None
        # 超类
        self.super_class = None
        # 接口
        self.interfaces = []
        # 实例变量所占空间
        self.instance_slot_count = 0
        # 类变量所占空间
        self.static_slot_count = 1
        # 静态变量
        self.static_vars = Slots()
        # 表示类的<clinit>方法是否已经开始执行
        self.init_started = False
        # 表示java.lang.Class实例
        self.j_class = None

    # 用来把classFile类转换成Class类
    @staticmethod
    def new_class(classFile: ClassFile):
        clazz = Class()
        clazz.access_flags = classFile.access_flags
        clazz.name = classFile.class_name
        clazz.super_class_name = classFile.super_class_name
        clazz.interface_names = classFile.interface_names
        clazz.constant_pool = ConstantPool.new_constant_pool(clazz, classFile.constant_pool)
        clazz.fields = Field.new_fields(clazz, classFile.fields)
        clazz.methods = Method.new_methods(clazz, classFile.methods)
        # 从class文件中读取源文件名
        clazz.source_file = get_source_file(classFile)
        return clazz

    # 用于判断public访问标志是否被设置
    def is_public(self):
        return 0 != self.access_flags & AccessFlags.ACC_PUBLIC

    # 用于判断final访问标志是否被设置
    def is_final(self):
        return 0 != self.access_flags & AccessFlags.ACC_FINAL

    # 用于判断super访问标志是否被设置
    def is_super(self):
        return 0 != self.access_flags & AccessFlags.ACC_SUPER

    # 用于判断interface访问标志是否被设置
    def is_interface(self):
        return 0 != self.access_flags & AccessFlags.ACC_INTERFACE

    # 用于判断abstract访问标志是否被设置
    def is_abstract(self):
        return 0 != self.access_flags & AccessFlags.ACC_ABSTRACT

    # 用于判断synthetic访问标志是否被设置
    def is_synthetic(self):
        return 0 != self.access_flags & AccessFlags.ACC_SYNTHETIC

    # 用于判断annotation访问标志是否被设置
    def is_annotation(self):
        return 0 != self.access_flags & AccessFlags.ACC_ANNOTATION

    # 用于判断enum访问标志是否被设置
    def is_enum(self):
        return 0 != self.access_flags & AccessFlags.ACC_ENUM

    # 类的访问控制权限
    def is_accessible_to(self, otherClass):
        """
        如果类D想访问类C，需要满足两个条件之一：C是pubilc，或者C和D在同一个运行时包内。
        :param otherClass:
        :return:
        """
        return self.is_public() or self.get_package_name() == otherClass.get_package_name()

    # 获取类所在的包名
    def get_package_name(self):
        i = self.name.rfind("/")
        if i >= 0:
            return self.name[:i]
        return ""

    def is_assignable_from(self, otherClass) -> bool:
        """
        在三种情况下，S类型的引用值可以赋值给T类型：S和T是同一类型；T是类且S是T的子类；或者T是接口且S实现了T接口
        :param otherClass:
        :return:
        """
        s, t = otherClass, self
        if s == t:
            return True

        if not s.is_array():
            if not s.is_interface():
                if not t.is_interface():
                    return s.is_sub_class_of(t)
                else:
                    return s.is_implements(t)
            else:
                if not t.is_interface():
                    return t.is_jl_object()
                else:
                    return t.is_super_interface_of(s)
        else:
            if not t.is_array():
                if not t.is_interface():
                    return t.is_jl_object()
                else:
                    return t.is_jl_cloneable() or t.is_jio_serializable()
            else:
                sc = s.component_class()
                tc = t.component_class()
                return sc == tc or tc.is_assignable_from(sc)

    # 判断S是否是T的子类，也就是判断T是否是S的（直接或间接）超类
    def is_sub_class_of(self, otherClass):
        c = self.super_class
        while c:
            if c == otherClass:
                return True
            c = c.super_class

        return False

    # 判断S是否实现了T接口
    def is_implements(self, iface):
        c = self
        while c:
            for interface in c.interfaces:
                if interface == iface or interface.is_sub_interface_of(iface):
                    return True

        return False

    # 判断S是否实现了T的子接口
    def is_sub_interface_of(self, iface):
        for super_interface in self.interfaces:
            if super_interface == iface or super_interface.is_sub_interface_of(iface):
                return True

        return False

    # 判断S是否是T的超类
    def is_super_class_of(self, otherClass):
        return otherClass.is_sub_class_of(self)

    def is_super_interface_of(self, iface):
        return iface.is_sub_interface_of(self)

    def get_main_method(self):
        return self.get_static_method("main", "([Ljava/lang/String;)V")

    def get_static_method(self, name, descriptor):
        for method in self.methods:
            if method.is_static() and method.name == name and method.descriptor == descriptor:
                return method
        return None

    def new_object(self):
        from runtime.heap.Object import Object
        return Object.new_object(self)

    def start_init(self):
        self.init_started = True

    def get_clinit_method(self):
        return self.get_static_method("<clinit>", "()V")

    # 数组类
    def new_array(self, count):
        from runtime.heap.Object import Object
        if not self.is_array():
            raise RuntimeError("Not array class: " + self.name)
        return Object(self, [0 for _ in range(count)])

    def is_array(self) -> bool:
        return self.name[0] == '['

    def is_jl_object(self):
        return self.name == "java/lang/Object"

    def is_jl_cloneable(self):
        return self.name == "java/lang/Cloneable"

    def is_jio_serializable(self):
        return self.name == "java/io/Serializable"

    def array_class(self):
        """
        先根据类名得到数组类名，然后调用类加载器加载数组类。
        :return:
        """
        array_class_name = ClassNameHelper.get_array_class_name(self.name)
        return self.loader.load_class(array_class_name)

    # 返回数组类的元素类型
    def component_class(self):
        """
        先根据数组类名推测出数组元素类名，然后用类加载器加载元素类
        :return:
        """
        component_class_name = self.get_component_class_name(self.name)
        return self.loader.load_class(component_class_name)

    @staticmethod
    def get_component_class_name(className):
        # 数组类名以[开头，把它去掉就是数组元素的类型描述符
        if className[0] == '[':
            component_type_descriptor = className[1:]
            return ClassNameHelper.to_class_name(component_type_descriptor)

    # 根据字段名和描述符查找方法
    def get_method(self, name, descriptor, is_static_flag):
        c = self
        while c:
            for method in c.methods:
                if method.is_static() == is_static_flag \
                        and method.name == name and method.descriptor == descriptor:
                    return method

            c = c.super_class
        return None

    # 根据字段名和描述符查找字段
    def get_field(self, name, descriptor, is_static_flag):
        c = self
        while c:
            for field in c.fields:
                if field.is_static() == is_static_flag \
                        and field.name == name and field.descriptor == descriptor:
                    return field

            c = c.super_class
        return None

    # 返回转换后的类名,self.name形如java/lang/Object，转换后为java.lang.Object
    @property
    def java_name(self):
        return self.name.replace("/", ".", -1)

    # 判断类是否是基本类型的类
    def is_primitive(self) -> bool:
        if PrimitiveTypes.get(self.name) is not None:
            return True
        return False

    def get_instance_method(self, name, descriptor):
        return self.get_method(name, descriptor, False)

    def get_ref_var(self, field_name, field_descriptor):
        field = self.get_field(field_name, field_descriptor, True)
        return self.static_vars.get_ref(field.slot_id)

    def set_ref_var(self, field_name, field_descriptor, ref):
        field = self.get_field(field_name, field_descriptor, True)
        self.static_vars.set_ref(field.slot_id, ref)
