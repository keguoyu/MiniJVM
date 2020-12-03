package com.keguoyu.minijvm.lang;

import com.sun.tools.classfile.ConstantPoolException;
import com.sun.tools.classfile.Descriptor;
import com.sun.tools.classfile.Field;

public class JvmObjectField implements JvmField {

    public final Field field;
    public final JvmClass<?> jvmClass;

    public Object value;

    public JvmObjectField(JvmClass<?> jvmClass, Field field) {
        this.jvmClass = jvmClass;
        this.field =  field;
        try {
            String fieldType = field.descriptor.getFieldType(jvmClass.classFile.constant_pool);
            switch (fieldType){
                case "byte":
                    value = (byte)0;
                    break;
                case "char":
                    value = '\u0000';
                    break;
                case "double":
                    value = 0.;
                    break;
                case "float":
                    value = 0.f;
                    break;
                case "int":
                    value = 0;
                    break;
                case "long":
                    value = 0L;
                    break;
                case "short":
                    value = (short)0;
                    break;
                case "value":
                    value = false;
                    break;
                default:
                    value = null;
                    break;
            }
        } catch (ConstantPoolException | Descriptor.InvalidDescriptor e) {
            e.printStackTrace();
        }
    }

    @Override
    public void set(Object me, Object value) {

    }

    @Override
    public Object get(Object me) {
        return null;
    }
}
