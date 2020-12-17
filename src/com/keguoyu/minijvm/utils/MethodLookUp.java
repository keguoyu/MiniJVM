package com.keguoyu.minijvm.utils;

import com.keguoyu.minijvm.lang.JvmClass;
import com.keguoyu.minijvm.lang.JvmMethod;

import java.util.Collection;

public class MethodLookUp {

    public static JvmMethod lookUpMethodInSelf(JvmClass<?> jvmClass, String methodType, String methodName) {
        Collection<JvmMethod> methods = jvmClass.methods.values();
        for (JvmMethod jvmMethod : methods) {
            if (methodType.equals(jvmMethod.getType()) && methodName.equals(jvmMethod.getMethodName())) {
                return jvmMethod;
            }
        }
        return null;
    }

    public static JvmMethod lookUpMethodInSuperClass(JvmClass<?> jvmClass, String methodType, String methodName) {
        JvmClass<?> superClass = jvmClass.superClass;
        JvmMethod method = null;
        while (superClass != null && method == null) {
            method = lookUpMethodInSelf(superClass, methodType, methodName);
            superClass = superClass.superClass;
        }
        return null;
    }

    public static JvmMethod lookUpMethodInInterfaces(JvmClass<?> jvmClass, String methodType, String methodName) {
        if (!jvmClass.interfaces.isEmpty()) {
            for (JvmClass<?> ifClass : jvmClass.interfaces) {
                JvmMethod method = lookUpMethodInSelf(ifClass, methodType, methodName);
                if (method != null) {
                    return method;
                }
            }
        }
        return null;
    }


}
