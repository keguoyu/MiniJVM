package com.keguoyu.minijvm.lang;

import java.util.Arrays;

public class JvmObject {
    public JvmClass<?> jvmClass;
    private final Object[] instanceFields;

    public JvmObject(JvmClass<?> jvmClass, int instanceFieldCount) {
        this.jvmClass = jvmClass;
        this.instanceFields = new Object[instanceFieldCount];
    }

    public void setInstanceFieldVal(int position, Object val) {
        this.instanceFields[position] = val;
    }

    public Object getInstanceFieldVal(int fieldIndex) {
        return instanceFields[fieldIndex];
    }

    @Override
    public String toString() {
        return "JvmObject{" +
                "jvmClass=" + jvmClass +
                ", instanceFields=" + Arrays.toString(instanceFields) +
                '}';
    }
}
