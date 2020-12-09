package com.keguoyu.minijvm.runtime;

import java.util.Arrays;

public class ConstantPool {
    private Object[] constants;
    private int position;

    public void makeConstants(int newSize) {
        if (constants == null) {
            constants = new Object[newSize];
        } else {
            constants = Arrays.copyOf(constants, newSize + constants.length);
        }
    }

    public int getOriginSize() {
        return constants == null ? 0 : constants.length;
    }

    public void set(int position, Object object) {
        constants[position] = object;
    }

    public Object get(int position) {
        Object constant = constants[position];
        if (constant != null) {
            return constant;
        }
        throw new RuntimeException("Can't find constant at " + position);
    }

}
