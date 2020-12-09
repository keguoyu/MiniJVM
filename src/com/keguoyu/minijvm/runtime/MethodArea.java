package com.keguoyu.minijvm.runtime;

import com.keguoyu.minijvm.lang.JvmClass;
import com.keguoyu.minijvm.pojo.ref.ClassRef;
import com.keguoyu.minijvm.pojo.ref.FieldRef;
import com.keguoyu.minijvm.pojo.ref.MethodRef;
import com.sun.tools.classfile.ClassFile;

import java.util.HashMap;
import java.util.Map;

/**
 * JVM方法区
 * 存放加载好的类信息 以及运行时常量池
 */
public class MethodArea {
    public final Map<String, JvmClass<?>> classCache = new HashMap<>();
    private final ConstantPool constantPool = new ConstantPool();

    public void put(String fullName, JvmClass<?> jvmClass) {
        classCache.put(fullName, jvmClass);
    }

    public JvmClass<?> get(String fullName) {
        return classCache.get(fullName);
    }


    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public void toRuntimeConstantPool(JvmClass<?> jvmClass) {
        final ClassFile classFile = jvmClass.classFile;
        final com.sun.tools.classfile.ConstantPool pool = classFile.constant_pool;
        com.sun.tools.classfile.ConstantPool.CPInfo cpInfo;
        for (int i = 1; i < pool.size(); i++) {
            try {
                cpInfo = pool.get(i);
                switch (cpInfo.getTag()) {
//                    case com.sun.tools.classfile.ConstantPool.CONSTANT_Class:
//                        ClassRef classRef = new ClassRef();
//                        classRef.constantPool = constantPool;
//                        classRef.fullName = ((com.sun.tools.classfile.ConstantPool.CONSTANT_Class_info) cpInfo).getName();
//                        constantPool.add(classRef);
//                        System.out.println("class =>> " + classRef.fullName);
//                        break;
//                    case com.sun.tools.classfile.ConstantPool.CONSTANT_Fieldref:
//                        com.sun.tools.classfile.ConstantPool.CONSTANT_Fieldref_info constantFieldrefInfo =
//                                (com.sun.tools.classfile.ConstantPool.CONSTANT_Fieldref_info) cpInfo;
//                        FieldRef fieldRef = new FieldRef();
//                        fieldRef.fullName = constantFieldrefInfo.getNameAndTypeInfo().getName();
//                        break;
//                    case com.sun.tools.classfile.ConstantPool.CONSTANT_Methodref:
//                        com.sun.tools.classfile.ConstantPool.CONSTANT_Methodref_info constant_methodref_info =
//                                (com.sun.tools.classfile.ConstantPool.CONSTANT_Methodref_info) cpInfo;
//                        MethodRef methodRef = new MethodRef();
//                        com.sun.tools.classfile.ConstantPool.CONSTANT_NameAndType_info constant_methodref_infoNameAndTypeInfo
//                                = constant_methodref_info.getNameAndTypeInfo();
//                        methodRef.fullName = constant_methodref_info.getNameAndTypeInfo().getName();
//                        System.out.println(constant_methodref_infoNameAndTypeInfo.getName());
//                        break;
//                    case com.sun.tools.classfile.ConstantPool.CONSTANT_InterfaceMethodref:
//                        break;
//                    case com.sun.tools.classfile.ConstantPool.CONSTANT_String:
//                        constantPool.add(((com.sun.tools.classfile.ConstantPool.CONSTANT_String_info) cpInfo).getString());
//                        break;
//                    case com.sun.tools.classfile.ConstantPool.CONSTANT_Integer:
//                        constantPool.add(((com.sun.tools.classfile.ConstantPool.CONSTANT_Integer_info) cpInfo).value);
//                        break;
//                    case com.sun.tools.classfile.ConstantPool.CONSTANT_Float:
//                        constantPool.add(((com.sun.tools.classfile.ConstantPool.CONSTANT_Float_info) cpInfo).value);
//                        break;
//                    case com.sun.tools.classfile.ConstantPool.CONSTANT_Long:
//                        constantPool.add(((com.sun.tools.classfile.ConstantPool.CONSTANT_Long_info) cpInfo).value);
//                        break;
//                    case com.sun.tools.classfile.ConstantPool.CONSTANT_Double:
//                        constantPool.add(((com.sun.tools.classfile.ConstantPool.CONSTANT_Double_info) cpInfo).value);
//                        break;
                }
            } catch (Exception ignore) {
            }
        }
    }

}
