package com.keguoyu.minijvm.data.ref;

import com.keguoyu.minijvm.lang.JvmMethod;

public class MethodRef {
    private JvmMethod jvmMethod;
    private String className;
    private String type;
    private String methodName;

    public MethodRef(String className, String type, String methodName) {
        this.className = className;
        this.type = type;
        this.methodName = methodName;
    }

    public void resolveMethod() {
        if (jvmMethod == null) {
            resolveMethodInternal();
        }
    }

    private void resolveMethodInternal() {

    }

    @Override
    public String toString() {
        return "MethodRef{" +
                "jvmMethod=" + jvmMethod +
                ", className='" + className + '\'' +
                ", type='" + type + '\'' +
                ", methodName='" + methodName + '\'' +
                '}';
    }
}
