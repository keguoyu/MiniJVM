package com.keguoyu.minijvm.runtime.data;

/**
 * 局部变量表
 */
public class LocalVariable {
    public Object[] slots;

    public LocalVariable(int length) {
        slots = new Object[length];
    }

    public void setObject(int index, Object obj) {
        slots[index] = obj;
    }

    public Object getObject(int index) {
        return slots[index];
    }

}
