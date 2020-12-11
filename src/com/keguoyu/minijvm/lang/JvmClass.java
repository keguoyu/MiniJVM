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

    private boolean mClinitCalled = false;

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

    public boolean isAssignFrom(JvmClass<?> another) {
        return this == another || isSubClassOf(another) || isImplementsOf(another);
    }

    public boolean isSubClassOf(JvmClass<?> another) {
        return superClass != null && superClass == another;
    }

    public boolean isImplementsOf(JvmClass<?> another) {
        for (JvmClass<?> jvmClass : interfaces) {
            if (jvmClass == another || jvmClass.isSubInterfaceOf(another)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSubInterfaceOf(JvmClass<?> another) {
        for (JvmClass<?> jvmClass : interfaces) {
            if (jvmClass == another || jvmClass.isSubInterfaceOf(another)) {
                return true;
            }
        }
        return false;
    }

    public boolean isInterface() {
        return classFile.access_flags.is(AccessFlags.ACC_INTERFACE);
    }

    public boolean isAbstract() {
        return classFile.access_flags.is(AccessFlags.ACC_ABSTRACT);
    }

    public JvmObject newInstance() {
        return new JvmObject(this, instanceFields.length);
    }

    /**
     * 执行Class的clinit方法
     *
     * 执行new指令创建类实例，但类还没有被初始化。
     * ·执行putstatic、getstatic指令存取类的静态变量，但声明该字段
     * 的类还没有被初始化。
     * ·执行invokestatic调用类的静态方法，但声明该方法的类还没
     * 有被初始化。
     * ·当初始化一个类时，如果类的超类还没有被初始化，要先初始化类的超类。
     * ·执行某些反射操作时。
     *
     */
    public void callClinit() {
        JvmMethod clinit = getMethod("<clinit>", "()V");
        if (clinit != null) {
            BytecodeInvoker.invoke(clinit);
        }
        mClinitCalled = true;
    }

    public boolean isClinitCalled() {
        return mClinitCalled;
    }

}
