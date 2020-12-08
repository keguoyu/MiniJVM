package com.keguoyu.minijvm.runtime;


import com.keguoyu.minijvm.runtime.data.StackFrame;

public class JvmStack {
    public final int maxStackSize;
    public int currentSize;
    public StackFrame topFrame;

    public JvmStack(int maxStackSize) {
        this.maxStackSize = maxStackSize;
    }

    public void push(StackFrame stackFrame) {
        if (currentSize >= maxStackSize) {
            throw new RuntimeException("java.lang.StackOverflowException");
        }
        if (topFrame != null) {
            stackFrame.bottom = topFrame;
        }
        topFrame = stackFrame;
        currentSize++;
    }

    public StackFrame pop() {
        checkStackEmpty();
        StackFrame top = topFrame;
        topFrame = top.bottom;
        currentSize--;
        return top;
    }

    public StackFrame currentFrame() {
        checkStackEmpty();
        return topFrame;
    }

    private void checkStackEmpty() {
        if (topFrame == null) {
            throw new RuntimeException("jvm stack empty");
        }
    }

}
