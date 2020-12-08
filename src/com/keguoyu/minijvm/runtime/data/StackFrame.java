package com.keguoyu.minijvm.runtime.data;

/**
 * 栈帧
 */
public class StackFrame {
    public StackFrame bottom;
    public LocalVariable localVariable;
    public OperationStack operationStack;
    public JvmThread jvmThread;

    public StackFrame(int maxLocals, int maxOperands) {
        localVariable = new LocalVariable(maxLocals);
        operationStack = new OperationStack(maxOperands);
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
}
