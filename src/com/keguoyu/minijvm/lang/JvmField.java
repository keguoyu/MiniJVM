package com.keguoyu.minijvm.lang;

import com.sun.tools.classfile.AccessFlags;
import com.sun.tools.classfile.ConstantPoolException;
import com.sun.tools.classfile.Descriptor;
import com.sun.tools.classfile.Field;

public class JvmField {

    public final JvmClass<?> jvmClass;

    public final Field field;

    private int fieldIndex;

    public JvmField(JvmClass<?> jvmClass, Field field) {
        this.jvmClass = jvmClass;
        this.field = field;
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
        String name = "";
        try {
            name = field.getName(jvmClass.classFile.constant_pool);
        } catch (ConstantPoolException e) {
            e.printStackTrace();
        }
        return name;
    }

    public String getType() {
        String type = "";
        try {
            type = field.descriptor.getFieldType(jvmClass.classFile.constant_pool);
        } catch (ConstantPoolException | Descriptor.InvalidDescriptor e) {
            e.printStackTrace();
        }
        return type;
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
