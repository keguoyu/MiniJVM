package com.keguoyu.minijvm.runtime;

import com.keguoyu.minijvm.cls.JVMClass;
import com.keguoyu.minijvm.pojo.ref.ClassRef;
import com.sun.tools.classfile.ClassFile;

import java.util.HashMap;
import java.util.Map;

/**
 * JVM方法区
 * 存放加载好的类信息 以及运行时常量池
 */
public class MethodArea {
    private final Map<String, JVMClass<?>> classCache = new HashMap<>();
    private final ConstantPool constantPool = new ConstantPool();

    public void put(String fullName, JVMClass<?> jvmClass) {
        classCache.put(fullName, jvmClass);
    }

    public JVMClass<?> get(String fullName) {
        return classCache.get(fullName);
    }

    public void parseRuntimeConstantPool(JVMClass<?> jvmClass) {
        final ClassFile classFile = jvmClass.classFile;
        final com.sun.tools.classfile.ConstantPool pool = classFile.constant_pool;
        com.sun.tools.classfile.ConstantPool.CPInfo cpInfo;
        System.out.println(pool.size());
        for (int i = 1; i < pool.size(); i++) {
            try {
                cpInfo = pool.get(i);
                System.out.println(i + " " + cpInfo);
                if (cpInfo instanceof com.sun.tools.classfile.ConstantPool.CONSTANT_Double_info) {
                    constantPool.add(((com.sun.tools.classfile.ConstantPool.CONSTANT_Double_info) cpInfo).value);
                } else if (cpInfo instanceof com.sun.tools.classfile.ConstantPool.CONSTANT_Float_info) {
                    constantPool.add(((com.sun.tools.classfile.ConstantPool.CONSTANT_Float_info) cpInfo).value);
                } else if (cpInfo instanceof com.sun.tools.classfile.ConstantPool.CONSTANT_Integer_info) {
                    constantPool.add(((com.sun.tools.classfile.ConstantPool.CONSTANT_Integer_info) cpInfo).value);
                } else if (cpInfo instanceof com.sun.tools.classfile.ConstantPool.CONSTANT_Long_info) {
                    constantPool.add(((com.sun.tools.classfile.ConstantPool.CONSTANT_Long_info) cpInfo).value);
                } else if (cpInfo instanceof com.sun.tools.classfile.ConstantPool.CONSTANT_String_info) {
                    constantPool.add(((com.sun.tools.classfile.ConstantPool.CONSTANT_String_info) cpInfo).getString());
                } else if (cpInfo instanceof com.sun.tools.classfile.ConstantPool.CONSTANT_Class_info) {
                    ClassRef classRef = new ClassRef();
                    classRef.constantPool = constantPool;
                    classRef.fullName = ((com.sun.tools.classfile.ConstantPool.CONSTANT_Class_info) cpInfo).getName();
                    constantPool.add(classRef);
                } else if (cpInfo instanceof com.sun.tools.classfile.ConstantPool.CONSTANT_Fieldref_info) {
                    com.sun.tools.classfile.ConstantPool.CONSTANT_NameAndType_info nameAndTypeInfo =
                            ((com.sun.tools.classfile.ConstantPool.CONSTANT_Fieldref_info) cpInfo).getNameAndTypeInfo();
                    System.out.println(nameAndTypeInfo.getName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(constantPool);
    }

}
