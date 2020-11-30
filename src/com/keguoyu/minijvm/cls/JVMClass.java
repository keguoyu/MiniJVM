package com.keguoyu.minijvm.cls;

import com.sun.tools.classfile.ClassFile;

public class JVMClass<T> {
    public final ClassFile classFile;
    public final JVMClassLoader classLoader;

    public JVMClass(ClassFile classFile, JVMClassLoader classLoader) {
        this.classFile = classFile;
        this.classLoader = classLoader;
    }

    public static JVMClass<?> forName(String fullName) {
        return null;
    }

}
