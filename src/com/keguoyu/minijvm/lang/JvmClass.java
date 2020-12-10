package com.keguoyu.minijvm.lang;

import com.sun.tools.classfile.*;

import java.util.*;

public class JvmClass<T> {
    public final ClassFile classFile;

    public final JvmClassLoader classLoader;

    public final String fullName;
    public JvmClass<?> superClass;
    public List<JvmClass<?>> interfaces = new ArrayList<>();

    public final Map<Map.Entry<String, Descriptor>, JvmField> fields = new HashMap<>();
    public final Map<Map.Entry<String, String>, JvmMethod> methods = new HashMap<>();

    private Object[] staticFields = null;

    private Object[] instanceFields = null;

    public boolean mClinitCalled = false;

    //当创建JvmClass的时候 类的所有信息都必要明确
    public JvmClass(ClassFile classFile, String className, JvmClassLoader jvmClassLoader) {
        this.classFile = classFile;
        this.fullName = className;
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
        int staticFieldCount = 0;
        int instanceFieldCount = 0;
        for (Field field: classFile.fields) {
            try {
                String name = field.getName(classFile.constant_pool);
                JvmField jvmField = new JvmField(this, field);
                if (field.access_flags.is(AccessFlags.ACC_STATIC)) {
                    jvmField.setFieldIndex(staticFieldCount);
                    staticFieldCount++;
                } else {
                    jvmField.setFieldIndex(instanceFieldCount);
                    instanceFieldCount++;
                }
                fields.put(new AbstractMap.SimpleEntry<>(name, field.descriptor), jvmField);
            } catch (ConstantPoolException e) {
                e.printStackTrace();
            }
        }
        staticFields = new Object[staticFieldCount];
        instanceFields = new Object[instanceFieldCount];
    }

    public void setStaticFieldVal(int position, Object obj) {
        staticFields[position] = obj;
    }

    public Object getStaticFieldVal(int position) {
        return staticFields[position];
    }

    public void setInstanceFieldVal(int position, Object obj) {
        instanceFields[position] = obj;
    }

    public Object getInstanceFieldVal(int position) {
        return instanceFields[position];
    }

    public JvmMethod getMethod(String name, String desc) {
        return methods.get(new AbstractMap.SimpleEntry<>(name, desc));
    }

    public static JvmClass<?> forName(String fullName) {
        return null;
    }

    public boolean isPublic() {
        return classFile.access_flags.is(AccessFlags.ACC_PUBLIC);
    }

    /**
     * 包名格式 com.aa.bb
     */
    public String getPackageName() {
        if (fullName != null && fullName.length() > 0) {
            String replace = fullName.replace("/", ".");
            System.out.println(replace);
            int lastIndexOf = replace.lastIndexOf(".");
            System.out.println(lastIndexOf);
            if (lastIndexOf > 0) {
                return replace.substring(0, lastIndexOf);
            }
        }
        return "";
    }

    public String getClassName() {
        return fullName;
    }

    public boolean isAccessTo(JvmClass<?> another) {
        return isPublic() || getPackageName().equals(another.getPackageName());
    }

    public boolean isInterface() {
        return classFile.access_flags.is(AccessFlags.ACC_INTERFACE);
    }

    public boolean isAbstract() {
        return classFile.access_flags.is(AccessFlags.ACC_ABSTRACT);
    }

    public Object newInstance() {
        return new JvmObject(instanceFields.length);
    }

    /**
     * 执行Class的clinit方法
     */
    public void callClinit() {
        if (!mClinitCalled) {
            JvmMethod clinit = getMethod("<clinit>", "()V");
            if (clinit != null) {
                BytecodeInvoker.invoke(clinit);
            }
            mClinitCalled = true;
        }
    }

}
