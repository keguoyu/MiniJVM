package com.keguoyu.minijvm.data.ref;

import com.keguoyu.minijvm.lang.JvmClass;
import com.keguoyu.minijvm.runtime.SingleConstantPool;

public class SymbolicRef {
    public SingleConstantPool singleConstantPool;
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
