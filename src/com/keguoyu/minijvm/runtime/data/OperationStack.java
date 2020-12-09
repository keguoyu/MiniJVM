package com.keguoyu.minijvm.runtime.data;

import java.util.Arrays;

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

    @Override
    public String toString() {
        return "OperationStack{" +
                "size=" + size +
                ", objects=" + Arrays.toString(objects) +
                '}';
    }
}
