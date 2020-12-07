package com.keguoyu.minijvm.operation;

import com.keguoyu.minijvm.lang.BytecodeReader;
import com.keguoyu.minijvm.runtime.data.StackFrame;

public enum SwapOperations implements Operation{

    //0x5f
    SWAP {
        @Override
        public void fetchOperands(BytecodeReader reader) {

        }

        @Override
        public void execute(StackFrame frame) {
            Object pop1 = frame.operationStack.pop();
            Object pop2 = frame.operationStack.pop();
            frame.operationStack.push(pop1);
            frame.operationStack.push(pop2);
        }
    };
}
