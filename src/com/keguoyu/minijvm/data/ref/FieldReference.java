package com.keguoyu.minijvm.data.ref;

import com.keguoyu.minijvm.lang.JvmClass;
import com.keguoyu.minijvm.lang.JvmClassLoader;
import com.keguoyu.minijvm.lang.JvmField;
import com.keguoyu.minijvm.runtime.RuntimeConstantPool;

import java.util.Collection;
import java.util.List;

/**
 * 字段引用
 */
public class FieldReference {
    public RuntimeConstantPool constantPool;
    public JvmClass<?> jvmClass;
    private JvmField jvmField;
    private final String className;
    private final String type;
    private final String fieldName;

    public FieldReference(String className, String type, String fieldName) {
        this.className = className;
        this.type = parseType(type);
        this.fieldName = fieldName;
    }

    private String parseType(String type) {
        String fullType = "";
        switch (type.charAt(0)) {
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
            default:
                fullType = type;
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
            System.out.println("name " + rawField.getName() + " type " + rawField.getType() + "  " + type);

//            if (rawField.getName().equals(fieldName)
//                    && (rawField.getType().equals(type.replace("/", ".")) || ("L"+rawField.getType().replace("/", ".")).equals(type))
//                    && rawField.jvmClass.getClassName().equals(className)) {
            if (fieldName.equals(rawField.getName()) && (type.replace("/", ".")).contains(rawField.getType())) {
                System.out.println("find " + fieldName);
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
