package com.keguoyu.minijvm.operation;

import com.keguoyu.minijvm.runtime.data.StackFrame;

public interface Operation {
    void fetchOperands();
    void execute(StackFrame frame);
}
