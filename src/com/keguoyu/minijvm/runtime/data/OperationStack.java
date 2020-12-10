package com.keguoyu.minijvm.runtime.data;

import java.util.Arrays;
import java.util.Stack;

/**
 * 操作数栈
 */
public class OperationStack {
    public int size;
    public Stack<Object> objects;

    public OperationStack(int maxSize) {
        objects = new Stack<>();
    }

    public void push(Object obj) {
        objects.push(obj);
    }

    public Object pop() {
        return objects.pop();
    }

    @Override
    public String toString() {
        return "OperationStack{" +
                "size=" + size +
                ", objects=" + objects +
                '}';
    }
}
