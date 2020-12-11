package com.keguoyu.minijvm.data.ref;

import com.keguoyu.minijvm.lang.JvmClass;
import com.keguoyu.minijvm.lang.JvmClassLoader;
import com.keguoyu.minijvm.runtime.SingleConstantPool;

/**
 * 从常量池的类符号引用解析而来
 */
public class ClassReference {
    public SingleConstantPool constantPool;
    public JvmClass<?> jvmClass;
    public String fullName;

    public JvmClass<?> loadClass() {
        if (jvmClass == null) {
            loadClassInternal();
        }
        return jvmClass;
    }

    private void loadClassInternal() {
        JvmClassLoader loader = constantPool.jvmClass.classLoader;
        JvmClass<?> loadClass = loader.loadClass("", fullName);
        if (loadClass == null || !loadClass.isAccessTo(constantPool.jvmClass)) {
            throw new RuntimeException("can't load class of " + fullName);
        }
        this.jvmClass = loadClass;
    }


}
