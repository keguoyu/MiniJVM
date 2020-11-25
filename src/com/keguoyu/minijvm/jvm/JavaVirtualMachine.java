package com.keguoyu.minijvm.jvm;

/**
 * @author keguoyu
 * @version 1.0
 **/
public final class JavaVirtualMachine {

    private String classPath;
    private String className;

    public JavaVirtualMachine(String classPath, String className) {
        this.classPath = classPath;
        this.className = className;
    }

    public void start() {

    }

}
