package com.keguoyu.minijvm.lang;


import com.keguoyu.minijvm.main.JavaVirtualMachine;
import com.keguoyu.minijvm.pojo.ref.ClassRef;
import com.keguoyu.minijvm.runtime.ConstantPool;
import com.keguoyu.minijvm.runtime.MethodArea;
import com.keguoyu.minijvm.utils.Debugger;
import com.sun.tools.classfile.ClassFile;
import com.sun.tools.classfile.ConstantPoolException;

/**
 * 类加载器
 * 开启了双亲委派
 * 自定义类加载器
 * MyClassLoader loader = new MyClassLoader(JavaVirtualMachine.getAppClassLoader());
 */
public abstract class JvmClassLoader {

    private final JvmClassLoader parent;

    public JvmClassLoader(JvmClassLoader parent) {
        this.parent = parent;
    }

    /**
     * 加载类的入口
     * 启用双亲委派模型
     * classPath如果没有指定 那么就将从bootPath,extPath,再到当前目录下去加载
     * 如果classPath指定 那么就去指定的目录下进行加载
     */
    public JvmClass<?> loadClass(String classPath, String className) {
        JvmClass<?> jvmClass = JavaVirtualMachine.getMethodArea().get(className);
        if (jvmClass != null) {
            return jvmClass;
        }
        if (parent != null) {
            jvmClass = parent.loadClass(classPath, className);
        }
        if (jvmClass == null) {
            jvmClass = findClass(classPath, className);
        }
        return jvmClass;
    }

    /**
     * 在双亲委派的模型中 defineClass在单次类加载中只会调用一次
     * 所以可以在这里进行一些操作
     */
    public JvmClass<?> defineClass(ClassFile classFile) {
        MethodArea methodArea = JavaVirtualMachine.getMethodArea();
        JvmClass<?> jvmClass = new JvmClass<>(classFile, this);
        String className = "";
        try {
            className = classFile.getName();
            transformRuntimeConstantPool(jvmClass);
        } catch (ConstantPoolException e) {
            Debugger.printlnError(e);
        }
        verify(className, jvmClass);
        return jvmClass;
    }

    /**
     * 模拟验证操作
     */
    private void verify(String className, JvmClass<?> jvmClass) {
        Debugger.printf("verify %1s success\n", className);
    }

    private void transformRuntimeConstantPool(JvmClass<?> jvmClass) throws ConstantPoolException {
        final MethodArea methodArea = JavaVirtualMachine.getMethodArea();
        final ConstantPool constantPool = methodArea.getConstantPool();
        final int originIndex = constantPool.getOriginSize();
        constantPool.makeConstants(jvmClass.classFile.constant_pool.size());
        for (int i = 0; i < jvmClass.classFile.constant_pool.size(); i++) {
            com.sun.tools.classfile.ConstantPool.CPInfo cpInfo = jvmClass.classFile.constant_pool.get(i);
            if (cpInfo != null) {
                int newPosition = originIndex + i;
                switch (cpInfo.getTag()) {
                    case com.sun.tools.classfile.ConstantPool.CONSTANT_Integer:
                        constantPool.set(newPosition, ((com.sun.tools.classfile.ConstantPool.CONSTANT_Integer_info) cpInfo).value);
                        break;
                    case com.sun.tools.classfile.ConstantPool.CONSTANT_Float:
                        constantPool.set(newPosition, ((com.sun.tools.classfile.ConstantPool.CONSTANT_Float_info) cpInfo).value);
                        break;
                    case com.sun.tools.classfile.ConstantPool.CONSTANT_Long:
                        constantPool.set(newPosition, ((com.sun.tools.classfile.ConstantPool.CONSTANT_Long_info) cpInfo).value);
                        break;
                    case com.sun.tools.classfile.ConstantPool.CONSTANT_Double:
                        constantPool.set(newPosition, ((com.sun.tools.classfile.ConstantPool.CONSTANT_Double_info) cpInfo).value);
                        break;
                    case com.sun.tools.classfile.ConstantPool.CONSTANT_String:
                        constantPool.set(newPosition, ((com.sun.tools.classfile.ConstantPool.CONSTANT_String_info) cpInfo).getString());
                        break;
                    case com.sun.tools.classfile.ConstantPool.CONSTANT_Class:
                        ClassRef classRef = newClassRef();
                        classRef.constantPool = constantPool;
                        classRef.fullName = ((com.sun.tools.classfile.ConstantPool.CONSTANT_Class_info) cpInfo).getName();
                        classRef.jvmClass = JavaVirtualMachine.getAppClassLoader().loadClass("", classRef.fullName);
                        break;
                }
            }

        }
    }

    private ClassRef newClassRef() {
        return new ClassRef();
    }

    abstract JvmClass<?> findClass(String classPath, String className);
}
