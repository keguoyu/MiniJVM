package com.keguoyu.minijvm.pojo.ref;

import com.keguoyu.minijvm.lang.JvmClass;
import com.keguoyu.minijvm.runtime.ConstantPool;

public class SymbolicRef {
    public ConstantPool constantPool;
    public JvmClass<?> jvmClass;
    public String fullName;

    @Override
    public String toString() {
        return "SymbolicRef{" +
                ", jvmClass=" + jvmClass +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
