package com.keguoyu.minijvm.runtime.data;

import com.keguoyu.minijvm.runtime.JvmStack;

public class JvmThread {

    public int pc;
    public JvmStack jvmStack;

    public JvmThread() {
        this.pc = 0;
        this.jvmStack = new JvmStack(1024);
    }

    public void pushFrame(StackFrame stackFrame) {
        jvmStack.push(stackFrame);
    }

    public void popFrame(StackFrame stackFrame) {
        jvmStack.pop(stackFrame);
    }

    public StackFrame current() {
        return jvmStack.currentFrame();
    }

}
