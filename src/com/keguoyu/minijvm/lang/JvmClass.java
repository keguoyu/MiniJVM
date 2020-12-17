package com.keguoyu.minijvm.lang;

import com.keguoyu.minijvm.runtime.RuntimeConstantPool;
import com.keguoyu.minijvm.utils.ArrayTypes;
import com.sun.tools.classfile.*;
import static com.keguoyu.minijvm.utils.ArrayTypes.*;

import java.util.*;

public class JvmClass<T> {
    public final ClassFile classFile;

    public final JvmClassLoader classLoader;

    public final String fullName;
    public JvmClass<?> superClass;
    public List<JvmClass<?>> interfaces = new ArrayList<>();

    public final Map<Map.Entry<String, Descriptor>, JvmField> fields = new HashMap<>();
    public final Map<Map.Entry<String, String>, JvmMethod> methods = new HashMap<>();

    public Object[] staticFields = null;

    public Object[] instanceFields = null;

    private boolean mClinitCalled = false;

    public RuntimeConstantPool constantPool;

    //当创建JvmClass的时候 类的所有信息都必要明确
    public JvmClass(ClassFile classFile, String className, JvmClassLoader jvmClassLoader) {
        this.classFile = classFile;
        this.fullName = className;
        this.classLoader= jvmClassLoader;
        if (classFile != null) {
            for (Method method : classFile.methods) {
                try {
                    String name = method.getName(classFile.constant_pool);
                    String desc = method.descriptor.getValue(classFile.constant_pool);
                    methods.put(new AbstractMap.SimpleEntry<>(name, desc), new JvmMethod(method, this));
                } catch (ConstantPoolException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void initStaticVals() {
        for (JvmField field: fields.values()) {
            if (field.isStatic()) {
                if (field.isFinal()) {
                    int constantIndex = field.getConstantIndex();
                    if (constantIndex >= 0) {
                        Object o = constantPool.get(constantIndex);
                        field.setValue(o);
                    }
                } else {
                    String type = field.getType();
                    switch (type) {
                        case "int":
                        case "byte":
                        case "short":
                            field.setValue(0);
                            break;
                        case "float":
                            field.setValue(0.0f);
                            break;
                        case "long":
                            field.setValue(0L);
                            break;
                        case "char":
                            field.setValue(' ');
                            break;
                        default:
                            field.setValue(null);
                    }
                }
            }
        }
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

    public boolean isSuperClassOf(JvmClass<?> another) {
        return another.isSubClassOf(this);
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

    public boolean isSuper() {
        return classFile.access_flags.is(AccessFlags.ACC_SUPER);
    }

    public JvmObject newInstance() {
        return new JvmObject(this, instanceFields.length);
    }

    public JvmArray newArray(int arrayType, int length) {
        switch (arrayType) {
            case ARRAY_BOOLEAN:
            case ARRAY_BYTE:
            case ARRAY_CHAR:
            case ARRAY_SHORT:
            case ARRAY_INT:
            case ARRAY_LONG:
            case ARRAY_FLOAT:
            case ARRAY_DOUBLE:
            default:
                return new JvmArray(new Object[length]);
//            default:
//                return new JvmArray(new JvmObject[length]);
        }
    }

    public JvmClass<?> getArrayClass() {
        return classLoader.loadClass("", getArrayClassName(getClassName()));
    }

    private String getArrayClassName(String className) {
        return "[" + toDescriptor(className);
    }

    private String toDescriptor(String className) {
        if (className.charAt(0) == '[') {
            // array
            return className;
        }
        if (primitiveTypes.containsKey(className)) {
            return primitiveTypes.get(className);
        }
        // object
        return "L" + className + ";";
    }

    public void setClinitCalled(boolean mClinitCalled) {
        this.mClinitCalled = mClinitCalled;
    }

    public boolean isClinitCalled() {
        return mClinitCalled;
    }

}
