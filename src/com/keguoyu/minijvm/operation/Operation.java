package com.keguoyu.minijvm.operation;

import com.keguoyu.minijvm.lang.BytecodeReader;
import com.keguoyu.minijvm.runtime.data.StackFrame;

public interface Operation {
    default void fetchOperands(BytecodeReader reader) {}
    void execute(StackFrame frame);
    String getCode();
}
