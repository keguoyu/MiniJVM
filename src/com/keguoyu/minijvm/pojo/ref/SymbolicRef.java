package com.keguoyu.minijvm.pojo.ref;

import com.keguoyu.minijvm.cls.JVMClass;
import com.keguoyu.minijvm.runtime.ConstantPool;

public class SymbolicRef {
    public ConstantPool constantPool;
    public JVMClass<?> jvmClass;
    public String fullName;

    @Override
    public String toString() {
        return "SymbolicRef{" +
                ", jvmClass=" + jvmClass +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
