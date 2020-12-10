package com.keguoyu.minijvm.runtime.data;

import com.keguoyu.minijvm.lang.JvmMethod;

/**
 * 栈帧
 */
public class StackFrame {
    public StackFrame bottom;
    public LocalVariable localVariable;
    public OperationStack operationStack;
    public JvmThread jvmThread;
    public JvmMethod jvmMethod;

    private int nextPC;

    public void setNextPC(int nextPC) {
        this.nextPC = nextPC;
    }

    public int getNextPC() {
        return nextPC;
    }

    public StackFrame(int maxLocals, int maxOperands, JvmMethod jvmMethod) {
        localVariable = new LocalVariable(maxLocals);
        operationStack = new OperationStack(maxOperands);
        this.jvmMethod = jvmMethod;
    }

    public void attachThread(JvmThread jvmThread) {
        this.jvmThread = jvmThread;
    }

    public JvmThread getJvmThread() {
        return jvmThread;
    }

    public LocalVariable getLocalVariable() {
        return localVariable;
    }

    public OperationStack getOperationStack() {
        return operationStack;
    }

    @Override
    public String toString() {
        return "StackFrame{" +
                "bottom=" + bottom +
                ", localVariable=" + localVariable +
                ", operationStack=" + operationStack +
                ", jvmThread=" + jvmThread +
                '}';
    }
}
