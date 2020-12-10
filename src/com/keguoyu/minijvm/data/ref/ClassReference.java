package com.keguoyu.minijvm.data.ref;

import com.keguoyu.minijvm.lang.JvmClass;
import com.keguoyu.minijvm.lang.JvmClassLoader;
import com.keguoyu.minijvm.runtime.SingleConstantPool;

public class ClassReference {
    public SingleConstantPool constantPool;
    public JvmClass<?> jvmClass;
    public String fullName;

    public void loadClass() {
        if (jvmClass == null) {
            loadClassInternal();
        }
    }

    private void loadClassInternal() {
        JvmClassLoader loader = constantPool.jvmClass.classLoader;
        JvmClass<?> loadClass = loader.loadClass("", fullName);
        if (loadClass == null || !loadClass.isAccessTo(constantPool.jvmClass)) {
            throw new RuntimeException("can't access");
        }
        this.jvmClass = loadClass;
    }


}
