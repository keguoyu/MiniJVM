package com.keguoyu.minijvm.lang;

public class JvmArray {

    private Object[] data;

    public JvmArray(Object[] data) {
        this.data = data;
    }

    public int length() {
        return data.length;
    }

    public Object get(int index) {
        return data[index];
    }

    public void set(int index, Object val) {
        data[index] = val;
    }

}
