package com.keguoyu.minijvm.runtime.data;

/**
 * 操作数栈
 */
public class OperationStack {
    public int size;
    public Object[] objects;

    public OperationStack(int maxSize) {
        objects = new Object[maxSize];
    }

    public void push(Object obj) {
        objects[size++] = obj;
    }

    public Object pop() {
        return objects[--size];
    }

}
