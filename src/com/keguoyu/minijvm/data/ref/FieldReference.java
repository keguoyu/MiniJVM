package com.keguoyu.minijvm.data.ref;

import com.keguoyu.minijvm.lang.JvmClass;
import com.keguoyu.minijvm.lang.JvmClassLoader;
import com.keguoyu.minijvm.lang.JvmField;
import com.keguoyu.minijvm.runtime.SingleConstantPool;

import java.util.Collection;
import java.util.List;

public class FieldReference {
    public SingleConstantPool constantPool;
    public JvmClass<?> jvmClass;
    private JvmField jvmField;
    private String className;
    private String type;
    private String fieldName;


    public FieldReference(String className, String type, String fieldName) {
        this.className = className;
        this.type = parseType(type.charAt(0));
        this.fieldName = fieldName;
    }

    private String parseType(char type) {
        String fullType = "";
        switch (type) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
                fullType = "int";
                break;
            case 'F':
                fullType = "float";
                break;
            case 'J':
                fullType = "long";
                break;
            case 'D':
                fullType = "double";
                break;
        }
        return fullType;
    }

    public JvmField resolveField() {
        if (jvmField == null) {
            resolveFieldInternal();
        }
        return jvmField;
    }

    private void resolveFieldInternal() {
        JvmClassLoader classLoader = constantPool.jvmClass.classLoader;
        this.jvmClass = classLoader.loadClass("", className);
        JvmField fieldByTraversal = findFieldByTraversal(jvmClass.fields.values());
        if (fieldByTraversal == null) {
            fieldByTraversal = findFieldByTraversal(jvmClass.superClass.fields.values());
        }
        if (fieldByTraversal == null) {
            List<JvmClass<?>> interfaces = jvmClass.superClass.interfaces;
            for (JvmClass<?> interfaceClass: interfaces) {
                fieldByTraversal = findFieldByTraversal(interfaceClass.fields.values());
                if (fieldByTraversal != null) {
                    break;
                }
            }
        }
        if (fieldByTraversal == null) {
            throw new RuntimeException("Can't resolve " + fieldName);
        }
        this.jvmField = fieldByTraversal;
    }

    private JvmField findFieldByTraversal(Collection<JvmField> fields) {
        for(JvmField rawField: fields) {
            System.out.println("name " + rawField.getName() + " type " + rawField.getType() );
            if (rawField.getName().equals(fieldName)
                    && rawField.getType().equals(type)
                    && rawField.jvmClass.getClassName().equals(className)) {
                return rawField;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "FieldRef{" +
                "jvmField=" + jvmField +
                ", className='" + className + '\'' +
                ", type='" + type + '\'' +
                ", fieldName='" + fieldName + '\'' +
                '}';
    }
}
