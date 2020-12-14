package com.keguoyu.minijvm.lang;

import com.keguoyu.minijvm.runtime.SingleConstantPool;

import com.keguoyu.minijvm.main.JavaVirtualMachine;
import com.keguoyu.minijvm.data.ref.ClassReference;
import com.keguoyu.minijvm.data.ref.FieldReference;
import com.keguoyu.minijvm.data.ref.InterfaceMethodRef;
import com.keguoyu.minijvm.data.ref.MethodRef;
import com.keguoyu.minijvm.runtime.MethodArea;
import com.keguoyu.minijvm.utils.Debugger;
import com.sun.tools.classfile.*;

import java.util.AbstractMap;
import java.util.Map;


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
        String className = "";
        try {
            className = classFile.getName();
        } catch (ConstantPoolException e) {
            Debugger.printlnError(e);
        }
        JvmClass<?> jvmClass = new JvmClass<>(classFile, className, this);
        methodArea.put(className, jvmClass);
        transformRuntimeConstantPool(jvmClass);
        verify(className, jvmClass);
        prepare(jvmClass);
        loadSuperClass(jvmClass);
        loadSuperInterfaces(jvmClass);
        return jvmClass;
    }

    private void loadSuperClass(JvmClass<?> jvmClass) {
        try {
            String superclassName = jvmClass.classFile.getSuperclassName();
            jvmClass.superClass = JavaVirtualMachine.getAppClassLoader().loadClass("", superclassName);
        } catch (ConstantPoolException e) {
            Debugger.printlnError(e);
        }
    }

    private void loadSuperInterfaces(JvmClass<?> jvmClass) {
        int[] interfaces = jvmClass.classFile.interfaces;
        for (int i = 0 ;i < interfaces.length; i++) {
            try {
                String interfaceName = jvmClass.classFile.getInterfaceName(i);
                jvmClass.interfaces.add(JavaVirtualMachine.getAppClassLoader().loadClass("", interfaceName));
            } catch (ConstantPoolException e) {
                Debugger.printlnError(e);
            }
        }
    }

    /**
     * 模拟验证操作
     */
    private void verify(String className, JvmClass<?> jvmClass) {
        Debugger.printf("verify %1s success\n", className);
    }

    private void prepare(JvmClass<?> jvmClass) {
        int staticFieldCount = 0;
        int instanceFieldCount = 0;
        for (Field field: jvmClass.classFile.fields) {
            try {
                ConstantValue_attribute attribute = (ConstantValue_attribute) field.attributes.get("ConstantValue");

                String name = field.getName(jvmClass.classFile.constant_pool);
                JvmField jvmField = new JvmField(jvmClass, field);
                if (field.access_flags.is(AccessFlags.ACC_STATIC)) {
                    jvmField.setFieldIndex(staticFieldCount);
                    staticFieldCount++;
                } else {
                    jvmField.setFieldIndex(instanceFieldCount);
                    instanceFieldCount++;
                }
                jvmClass.fields.put(new AbstractMap.SimpleEntry<>(name, field.descriptor), jvmField);
            } catch (ConstantPoolException e) {
                e.printStackTrace();
            }
        }
        jvmClass.staticFields = new Object[staticFieldCount];
        jvmClass.instanceFields = new Object[instanceFieldCount];
        jvmClass.initStaticVals();
    }

    private void transformRuntimeConstantPool(JvmClass<?> jvmClass) {
        final MethodArea methodArea = JavaVirtualMachine.getMethodArea();

        ConstantPool classFileConstantsPool = jvmClass.classFile.constant_pool;
        final SingleConstantPool singleConstantPool = methodArea.getConstantPool(jvmClass, classFileConstantsPool.size());
        classFileConstantsPool.size();
        for (int i = 1; i < jvmClass.classFile.constant_pool.size(); i++) {
            ConstantPool.CPInfo cpInfo;
            try {
                cpInfo = jvmClass.classFile.constant_pool.get(i);
            if (cpInfo != null) {
                switch (cpInfo.getTag()) {
                    case ConstantPool.CONSTANT_Integer:
                        singleConstantPool.set(i, ((ConstantPool.CONSTANT_Integer_info) cpInfo).value);
                        break;
                    case ConstantPool.CONSTANT_Float:
                        singleConstantPool.set(i, ((ConstantPool.CONSTANT_Float_info) cpInfo).value);
                        break;
                    case ConstantPool.CONSTANT_Long:
                        singleConstantPool.set(i, ((ConstantPool.CONSTANT_Long_info) cpInfo).value);
                        break;
                    case ConstantPool.CONSTANT_Double:
                        singleConstantPool.set(i, ((ConstantPool.CONSTANT_Double_info) cpInfo).value);
                        break;
                    case ConstantPool.CONSTANT_String:
                        singleConstantPool.set(i, ((ConstantPool.CONSTANT_String_info) cpInfo).getString());
                        break;
                    case ConstantPool.CONSTANT_Class:
                        ClassReference classReference = newClassRef();
                        classReference.constantPool = singleConstantPool;
                        classReference.fullName = ((ConstantPool.CONSTANT_Class_info) cpInfo).getName();
                        singleConstantPool.set(i, classReference);
                        break;
                    case ConstantPool.CONSTANT_Fieldref:
                        ConstantPool.CONSTANT_Fieldref_info fieldrefInfo = (ConstantPool.CONSTANT_Fieldref_info) cpInfo;
                        ConstantPool.CONSTANT_Class_info fieldRefClassInfo =
                                classFileConstantsPool.getClassInfo(fieldrefInfo.class_index);
                        ConstantPool.CONSTANT_NameAndType_info fieldRefNameAndTypeInfo =
                                classFileConstantsPool.getNameAndTypeInfo(fieldrefInfo.name_and_type_index);
                        FieldReference fieldReference = newFieldRef(fieldRefClassInfo.getName(),
                                fieldRefNameAndTypeInfo.getType(), fieldRefNameAndTypeInfo.getName());
                        fieldReference.jvmClass = jvmClass;
                        fieldReference.constantPool = singleConstantPool;
                        singleConstantPool.set(i, fieldReference);
                        break;
                    case ConstantPool.CONSTANT_Methodref:
                        ConstantPool.CONSTANT_Methodref_info methodrefInfo = (ConstantPool.CONSTANT_Methodref_info) cpInfo;
                        ConstantPool.CONSTANT_Class_info methodRefClsInfo = classFileConstantsPool.getClassInfo(methodrefInfo.class_index);
                        ConstantPool.CONSTANT_NameAndType_info methodRefNameAndTypeInfo =
                                classFileConstantsPool.getNameAndTypeInfo(methodrefInfo.name_and_type_index);
                        MethodRef methodRef = newMethodRef(methodRefClsInfo.getName(),
                                methodRefNameAndTypeInfo.getType(), methodRefNameAndTypeInfo.getName());
                        methodRef.constantPool = singleConstantPool;
                        singleConstantPool.set(i, methodRef);
                        break;
                    case ConstantPool.CONSTANT_InterfaceMethodref:
                        ConstantPool.CONSTANT_InterfaceMethodref_info interfaceMethodrefInfo =
                                (ConstantPool.CONSTANT_InterfaceMethodref_info) cpInfo;
                        InterfaceMethodRef interfaceMethodRef = newInterfaceMethodRef();
                        interfaceMethodRef.className = interfaceMethodrefInfo.getClassName();
                        interfaceMethodRef.methodName = interfaceMethodrefInfo.getNameAndTypeInfo().getName();
                        interfaceMethodRef.type = interfaceMethodrefInfo.getNameAndTypeInfo().getType();
                        singleConstantPool.set(i, interfaceMethodRef);
                        break;
                }
            }  } catch (ConstantPoolException invalidIndex) {
                Debugger.printlnError(invalidIndex);
            }
        }
    }

    private ClassReference newClassRef() {
        return new ClassReference();
    }

    private FieldReference newFieldRef(String className, String type, String fieldName) {
        return new FieldReference(className, type, fieldName);
    }

    private MethodRef newMethodRef(String className, String type, String methodName) {
        return new MethodRef(className, type, methodName);
    }

    private InterfaceMethodRef newInterfaceMethodRef() {
        return new InterfaceMethodRef();
    }

    protected String tryAppendClassSub(String className) {
        String newClassName = className;
        if (!newClassName.endsWith(".class")) {
             newClassName= className  + ".class";
        }
        return newClassName;
    }

    abstract JvmClass<?> findClass(String classPath, String className);
}
