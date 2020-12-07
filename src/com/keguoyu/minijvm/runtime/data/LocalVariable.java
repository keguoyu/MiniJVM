package com.keguoyu.minijvm.runtime.data;

/**
 * 局部变量表
 */
public class LocalVariable {
    public Object[] slots;

    public LocalVariable(int length) {
        slots = new Object[length];
    }

    public void set(int index, Object obj) {
        slots[index] = obj;
    }

    public Object get(int index) {
        return slots[index];
    }

}
