package com.keguoyu.minijvm.lang;

public class JvmObject {
    public JvmClass<?> jvmClass;
    private int instanceFieldCount;

    public JvmObject(JvmClass<?> jvmClass, int instanceFieldCount) {
        this.jvmClass = jvmClass;
        this.instanceFieldCount = instanceFieldCount;
    }
}
