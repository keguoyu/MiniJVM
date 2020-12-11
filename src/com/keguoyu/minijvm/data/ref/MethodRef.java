package com.keguoyu.minijvm.data.ref;

import com.keguoyu.minijvm.lang.JvmClass;
import com.keguoyu.minijvm.lang.JvmClassLoader;
import com.keguoyu.minijvm.lang.JvmMethod;
import com.keguoyu.minijvm.runtime.SingleConstantPool;

import java.util.Collection;

public class MethodRef {
    public SingleConstantPool constantPool;
    public JvmMethod jvmMethod;
    private final String className;
    private final String type;
    private final String methodName;
    private JvmClass<?> jvmClass;

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

    public void resolveInterfaceMethod() {
        if (jvmMethod == null) {
            resolveInterfaceMethodInternal();
        }
    }

    private void resolveInterfaceMethodInternal(){
        JvmClassLoader classLoader = constantPool.jvmClass.classLoader;
        this.jvmClass = classLoader.loadClass("", className);
        for (JvmClass<?> interfaceCls: jvmClass.interfaces) {
            this.jvmMethod = findMethodByTraversal(interfaceCls.methods.values());
            if (jvmMethod != null) {
                break;
            }
        }
        if (jvmMethod == null) {
            throw new RuntimeException("Can't resolve method " + methodName);
        }
    }

    private void resolveMethodInternal() {
        JvmClassLoader classLoader = constantPool.jvmClass.classLoader;
        this.jvmClass = classLoader.loadClass("", className);
        this.jvmMethod = findMethodByTraversal(jvmClass.methods.values());
        if (jvmMethod == null) {
            this.jvmMethod = findMethodByTraversal(jvmClass.superClass.methods.values());
        }
        if (jvmMethod == null) {
            for (JvmClass<?> interfaceCls: jvmClass.interfaces) {
                this.jvmMethod = findMethodByTraversal(interfaceCls.methods.values());
                if (jvmMethod != null) {
                    break;
                }
            }
        }
        if (jvmMethod == null) {
            throw new RuntimeException("Can't resolve method " + methodName);
        }
    }

    private JvmMethod findMethodByTraversal(Collection<JvmMethod> methods) {
        for (JvmMethod method: methods) {
            if (methodName.equals(method.getName()) && (type.replace("/", ".")).contains(method.getType())) {
                System.out.println("find " + methodName);
                return method;
            }
        }
        return null;
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
