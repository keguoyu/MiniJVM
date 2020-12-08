package com.keguoyu.minijvm.operation;

import com.keguoyu.minijvm.runtime.data.StackFrame;

public enum Goto {

    GOTO;

    public void branch(StackFrame frame, int offset) {
        int curPc = frame.getJvmThread().getPc();
        int nextPc = curPc + offset;

    }
}
