package com.keguoyu.minijvm.lang;

public interface JvmField {
    void set(Object me, Object value);
    Object get(Object me);
}
