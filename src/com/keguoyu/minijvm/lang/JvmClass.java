package com.keguoyu.minijvm.lang;

import com.sun.tools.classfile.ClassFile;
import com.sun.tools.classfile.ConstantPoolException;
import com.sun.tools.classfile.Descriptor;
import com.sun.tools.classfile.Method;

import java.util.*;

public class JvmClass<T> {
    public final ClassFile classFile;

    public final JvmClassLoader classLoader;
    public JvmClass<?> superClass;
    public List<JvmClass<?>> interfaces = new ArrayList<>();

    public final Map<Map.Entry<String, Descriptor>, JvmField> fields = new HashMap<>();
    public final Map<Map.Entry<String, String>, JvmMethod> methods = new HashMap<>();

    public JvmClass(ClassFile classFile, JvmClassLoader jvmClassLoader) {
        this.classFile = classFile;
        this.classLoader= jvmClassLoader;
        for (Method method: classFile.methods) {
            try {
                String name = method.getName(classFile.constant_pool);
                String desc = method.descriptor.getValue(classFile.constant_pool);
                methods.put(new AbstractMap.SimpleEntry<>(name, desc), new JvmMethod(method, this));
            } catch (ConstantPoolException e) {
                e.printStackTrace();
            }
        }
    }

    public JvmMethod getMethod(String name, String desc) {
        return methods.get(new AbstractMap.SimpleEntry<>(name, desc));
    }

    public static JvmClass<?> forName(String fullName) {
        return null;
    }

}
