package com.keguoyu.minijvm.main;

import com.keguoyu.minijvm.lang.*;
import com.keguoyu.minijvm.operation.OperationFactory;
import com.keguoyu.minijvm.operation.StoreOperations;
import com.keguoyu.minijvm.runtime.MethodArea;
import com.sun.tools.classfile.Attribute;
import com.sun.tools.classfile.Code_attribute;
import sun.tools.java.Constants;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public final class JavaVirtualMachine {
    private static final JvmClassLoader appClassLoader;

    /**
     * 方法区 所有线程共享
     * 整个虚拟机只有一块
     */
    private static final MethodArea methodArea = new MethodArea();

    static {
        JvmClassLoader bootLoader = new BootPathJvmClassLoader();
        JvmClassLoader extLoader = new ExtPathJvmClassLoader(bootLoader);
        appClassLoader = new UserPathJvmClassLoader(extLoader);
    }

    public static JvmClassLoader getAppClassLoader() {
        return appClassLoader;
    }

    public static MethodArea getMethodArea() {
        return methodArea;
    }

    public JavaVirtualMachine() {
    }

    public void start(String classPath, String className) {
        OperationFactory.checkInitOrNot();
        JvmClass<?> jvmClass = appClassLoader.loadClass(classPath, className);
        JvmMethod main = jvmClass.getMethod("main",
                "([Ljava/lang/String;)V");
        main.invoke();
    }

}
