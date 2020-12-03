package com.keguoyu.minijvm.lang;

import com.sun.tools.classfile.Field;

public class JvmStaticField implements JvmField {

    public final Field field;
    public final JvmClass<?> jvmClass;

    public JvmStaticField(JvmClass<?> jvmClass, Field field) {
        this.jvmClass = jvmClass;
        this.field =  field;
    }

    @Override
    public void set(Object me, Object value) {

    }

    @Override
    public Object get(Object me) {
        return null;
    }
}
