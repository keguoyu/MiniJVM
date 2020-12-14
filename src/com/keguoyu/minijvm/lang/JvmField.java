package com.keguoyu.minijvm.lang;

import com.sun.tools.classfile.*;

public class JvmField {

    public final JvmClass<?> jvmClass;
    public final Field field;

    private String fieldName;
    private String fieldType;
    private int fieldIndex;

    private int constantIndex;

    private Object value;

    public JvmField(JvmClass<?> jvmClass, Field field) {
        this.jvmClass = jvmClass;
        this.field = field;
        ConstantValue_attribute attribute = (ConstantValue_attribute) field.attributes.get("ConstantValue");
        if (attribute != null) {
            constantIndex = attribute.constantvalue_index;
        }
        initFieldName();
        initType();
    }

    public void setFieldIndex(int fieldIndex) {
        this.fieldIndex = fieldIndex;
    }

    public Field getField() {
        return field;
    }

    public int getFieldIndex() {
        return fieldIndex;
    }

    public int getConstantIndex() {
        return constantIndex;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public String getName() {
        return fieldName;
    }

    public String getType() {
        return fieldType;
    }

    private void initFieldName() {
        String fieldName = "";
        try {
            fieldName = field.getName(jvmClass.classFile.constant_pool);
        } catch (ConstantPoolException e) {
            e.printStackTrace();
        }
        this.fieldName = fieldName;
    }

    public void initType() {
        String type = "";
        try {
            type = field.descriptor.getFieldType(jvmClass.classFile.constant_pool);
        } catch (ConstantPoolException | Descriptor.InvalidDescriptor e) {
            e.printStackTrace();
        }
        this.fieldType = type;
    }

    public boolean isPublic() {
        return field.access_flags.is(AccessFlags.ACC_PUBLIC);
    }

    public boolean isStatic() {
        return field.access_flags.is(AccessFlags.ACC_STATIC);
    }

    public boolean isFinal() {
        return field.access_flags.is(AccessFlags.ACC_FINAL);
    }



}
