package com.keguoyu.minijvm.lang;

import com.sun.tools.classfile.AccessFlags;
import com.sun.tools.classfile.ConstantPoolException;
import com.sun.tools.classfile.Descriptor;
import com.sun.tools.classfile.Field;

public class JvmField {

    public final JvmClass<?> jvmClass;
    public final Field field;

    private String fieldName;
    private String fieldType;
    private int fieldIndex;

    public JvmField(JvmClass<?> jvmClass, Field field) {
        this.jvmClass = jvmClass;
        this.field = field;
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
