package com.keguoyu.minijvm.runtime.data;

import com.keguoyu.minijvm.runtime.JvmStack;

/**
 * JvmThread表示一个线程
 * pc表示要执行的下一条指令 jvmStack表示虚拟机栈
 */
public class JvmThread {
    //执行完指令之后会更新一次
    private int pc;
    private final JvmStack jvmStack;

    public JvmThread() {
        this.pc = 0;
        this.jvmStack = new JvmStack(1024);
    }

    public void pushFrame(StackFrame stackFrame) {
        stackFrame.attachThread(this);
        jvmStack.push(stackFrame);
    }

    public StackFrame popFrame() {
        return jvmStack.pop();
    }

    public StackFrame current() {
        return jvmStack.currentFrame();
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public int getPc() {
        return pc;
    }
}
