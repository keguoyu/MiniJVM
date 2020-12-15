package com.keguoyu.minijvm.runtime.data;

import java.util.Stack;

/**
 * 操作数栈
 */
public class OperationStack {
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

    public int size() {
        return objects.size();
    }

    public Object getObjAt(int pos) {
        return objects.get(pos);
    }

    @Override
    public String toString() {
        return "OperationStack{" +
                ", objects=" + objects +
                '}';
    }
}
