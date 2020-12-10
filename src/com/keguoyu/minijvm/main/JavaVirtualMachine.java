package com.keguoyu.minijvm.main;

import com.keguoyu.minijvm.lang.*;
import com.keguoyu.minijvm.operation.OperationFactory;
import com.keguoyu.minijvm.runtime.MethodArea;

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
        jvmClass.callClinit();
        JvmMethod main = jvmClass.getMethod("main",
                "([Ljava/lang/String;)V");
        main.invoke();
    }

}
