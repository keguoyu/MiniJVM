package com.keguoyu.minijvm.runtime;

import com.keguoyu.minijvm.lang.JvmClass;

import java.util.HashMap;
import java.util.Map;

/**
 * JVM方法区 根据虚拟机规范 运行时常量池也放在这里
 * 存放加载好的类信息 以及运行时常量池
 */
public class MethodArea {
    public final Map<String, JvmClass<?>> classCache = new HashMap<>();

    //运行时常量池
    //class作为访问运行时常量池的入口
    private final Map<JvmClass<?>, SingleConstantPool> runtimeConstantPool = new HashMap<>();

    public void put(String fullName, JvmClass<?> jvmClass) {
        classCache.put(fullName, jvmClass);
    }

    public JvmClass<?> get(String fullName) {
        return classCache.get(fullName);
    }

    private SingleConstantPool checkPoolNull(JvmClass<?> jvmClass, int targetSize) {
        SingleConstantPool singleConstantPool = runtimeConstantPool.get(jvmClass);
        if (singleConstantPool == null && targetSize != -1) {
            singleConstantPool = new SingleConstantPool(jvmClass, targetSize);
            jvmClass.constantPool = singleConstantPool;
            runtimeConstantPool.put(jvmClass, singleConstantPool);
        }
        if (singleConstantPool == null) {
            throw new RuntimeException("constantPool not init");
        }
        return singleConstantPool;
    }

    public SingleConstantPool getConstantPool(JvmClass<?> jvmClass, int targetSize) {
        return checkPoolNull(jvmClass, targetSize);
    }

    public SingleConstantPool getConstantPool(JvmClass<?> jvmClass) {
        return getConstantPool(jvmClass, -1);
    }

}
