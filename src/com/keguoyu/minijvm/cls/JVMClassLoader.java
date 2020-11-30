package com.keguoyu.minijvm.cls;

import com.sun.tools.classfile.ClassFile;
import com.sun.tools.classfile.ConstantPoolException;

import java.io.File;
import java.io.IOException;

/**
 * 模拟Java原始的ClassLoader
 *
 * @author keguoyu
 */
public class JVMClassLoader {

    /**
     * 加载全限定类名的Class 因为实现的比较简单 未开启双亲委派
     *
     * @param fullName 全限定类名
     */
    public JVMClass<?> loadClass(String fullName) throws IOException, ConstantPoolException {
        return findClass(fullName);
    }

    public JVMClass<?> findClass(String fullName) throws IOException, ConstantPoolException {
        File file = new File(fullName);
        ClassFile classFile = ClassFile.read(file);
        return defineClass(classFile);
    }

    public final JVMClass<?> defineClass(ClassFile classFile) {
        return new JVMClass<>(classFile, this);
    }

}
