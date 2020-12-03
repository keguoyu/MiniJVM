package com.keguoyu.minijvm.runtime;

import com.keguoyu.minijvm.lang.JvmClass;

import java.util.ArrayList;
import java.util.List;

public class ConstantPool {
    private JvmClass<?> jvmClass;
    private List<Object> constants;

    public ConstantPool() {
        constants = new ArrayList<>();
    }

    public void add(Object obj) {
        constants.add(obj);
    }

    @Override
    public String toString() {
        return "ConstantPool{" +
                "jvmClass=" + jvmClass +
                ", constants=" + constants +
                '}';
    }
}
