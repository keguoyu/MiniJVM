package com.keguoyu.minijvm.main;

import com.keguoyu.minijvm.lang.*;
import com.keguoyu.minijvm.operation.OperationFactory;
import com.keguoyu.minijvm.runtime.MethodArea;

/**
 * 虚拟机实例
 */
public final class JavaVirtualMachine {
    private static final JvmClassLoader appClassLoader;

    /**
     * 方法区 所有线程共享
     * 整个虚拟机只有一块
     */
    private static final MethodArea methodArea = new MethodArea();

    static {
        //双亲委派模型
        //实际上Java的类加载并不是通过这种方式构建 旨在说明问题
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
        JvmMethod main = jvmClass.getMethod("main", "([Ljava/lang/String;)V");
        if (main != null) {
            main.invoke();
        } else {
            System.err.println("Can't find main method");
            System.exit(0);
        }
    }

}
