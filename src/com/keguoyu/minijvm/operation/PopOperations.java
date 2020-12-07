package com.keguoyu.minijvm.operation;

import com.keguoyu.minijvm.lang.BytecodeReader;
import com.keguoyu.minijvm.runtime.data.StackFrame;

public enum PopOperations implements Operation {

    //0x57
    POP {
        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.pop();
        }
    },

    //0x58
    POP2 {
        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.pop();
            frame.operationStack.pop();
        }
    };

    @Override
    public void fetchOperands(BytecodeReader reader) {
    }
}
