package com.keguoyu.minijvm.jvm;

import com.keguoyu.minijvm.cls.JVMClass;
import com.keguoyu.minijvm.cls.JVMClassLoader;
import com.keguoyu.minijvm.runtime.MethodArea;
import com.sun.tools.classfile.ConstantPoolException;

import java.io.File;
import java.io.IOException;

public final class JavaVirtualMachine {
    private final JVMClassLoader jvmClassLoader = new JVMClassLoader();

    /**
     * 方法区 所有线程共享
     */
    private final MethodArea methodArea = new MethodArea();

    public void start(String classPath, String className) {
        String fullName = classPath + File.separator + className;
        try {
            JVMClass<?> jvmClass = tryGetJVMClassFromMethodArea(fullName);
        } catch (IOException | ConstantPoolException e) {
            e.printStackTrace();
        }
    }

    private JVMClass<?> tryGetJVMClassFromMethodArea(String fullName) throws IOException, ConstantPoolException {
        JVMClass<?> jvmClass = methodArea.get(fullName);
        if (jvmClass == null) {
            jvmClass = jvmClassLoader.loadClass(fullName);
            methodArea.parseRuntimeConstantPool(jvmClass);
            methodArea.put(fullName, jvmClass);
        }
        return jvmClass;
    }

}
