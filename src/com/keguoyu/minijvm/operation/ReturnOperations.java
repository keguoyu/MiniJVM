package com.keguoyu.minijvm.operation;

import com.keguoyu.minijvm.lang.BytecodeReader;
import com.keguoyu.minijvm.runtime.data.StackFrame;

public enum ReturnOperations implements Operation{

    //0xb1
    RETURN {
        @Override
        public void execute(StackFrame frame) {
            frame.jvmThread.popFrame();
        }

        @Override
        public String getCode() {
            return "0xb1";
        }
    }
    ;

    @Override
    public void fetchOperands(BytecodeReader reader) {

    }
}
