package com.keguoyu.minijvm.operation;

import com.keguoyu.minijvm.runtime.data.StackFrame;

public enum Goto {

    GOTO;

    public void branch(StackFrame frame, int offset) {
        int pc = frame.getJvmThread().getPc();
        pc = pc + offset;
        frame.setNextPC(pc);
    }
}
