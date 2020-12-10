package com.keguoyu.minijvm.runtime;

import com.keguoyu.minijvm.lang.JvmClass;

public class SingleConstantPool {

    public final JvmClass<?> jvmClass;
    private final Object[] constantPool;

    public SingleConstantPool(JvmClass<?> jvmClass, int targetSize) {
        this.jvmClass = jvmClass;
        this.constantPool = new Object[targetSize];
    }

    public void set(int position, Object obj) {
        checkArrayIndex(position);
        constantPool[position] = obj;
    }

    public Object get(int position) {
        checkArrayIndex(position);
        return constantPool[position];
    }

    private void checkArrayIndex(int position) {
        if (position >= constantPool.length) {
            throw new RuntimeException("ArrayIndexOutOfBounds");
        }
    }

}
