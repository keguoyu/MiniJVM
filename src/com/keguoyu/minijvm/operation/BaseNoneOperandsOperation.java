package com.keguoyu.minijvm.operation;

import com.keguoyu.minijvm.lang.BytecodeReader;

public abstract class BaseNoneOperandsOperation implements Operation {
    @Override
    public void fetchOperands(BytecodeReader reader) {
    }
}
