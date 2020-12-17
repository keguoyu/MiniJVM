package com.keguoyu.minijvm.data.ref;

import com.keguoyu.minijvm.lang.JvmClass;
import com.keguoyu.minijvm.lang.JvmMethod;
import com.keguoyu.minijvm.runtime.RuntimeConstantPool;
import com.keguoyu.minijvm.utils.MethodLookUp;

public class InterfaceMethodRef {
    public String className;
    public String type;
    public String methodName;
    public RuntimeConstantPool constantPool;
    public JvmMethod jvmMethod;

    public JvmMethod resolveInterfaceMethod() {
        if (jvmMethod == null) {
            resolveInterfaceMethodInternal();
        }
        return jvmMethod;
    }

    private void resolveInterfaceMethodInternal() {
        JvmClass<?> jvmClass = constantPool.jvmClass.classLoader.loadClass("", className);
        if (!jvmClass.isInterface()) {
            throw new RuntimeException("java.lang.IncompatibleClassChangeError");
        }
        jvmMethod = MethodLookUp.lookUpMethodInSelf(jvmClass, type, methodName);
        if (jvmMethod == null) {
            jvmMethod = MethodLookUp.lookUpMethodInInterfaces(jvmClass, type, methodName);
        }
        if (jvmMethod == null) {
            throw new RuntimeException("java.lang.NoSuchMethodError");
        }

    }




}
