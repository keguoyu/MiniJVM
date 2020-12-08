package com.keguoyu.minijvm.lang;

import com.sun.tools.classfile.Method;

public class JvmMethod {
    public final Method method;
    public final JvmClass<?> jvmClass;

    public JvmMethod(Method method, JvmClass<?> jvmClass) {
        this.method = method;
        this.jvmClass = jvmClass;
    }
}
