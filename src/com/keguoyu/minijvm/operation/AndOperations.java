package com.keguoyu.minijvm.operation;

import com.keguoyu.minijvm.lang.BytecodeReader;
import com.keguoyu.minijvm.runtime.data.StackFrame;

public enum AndOperations implements Operation{

    //0x7e
    IAND {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            int v2 = (int) frame.operationStack.pop();
            int result = v1 & v2;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x7e";
        }
    },

    //0x7f
    LAND {
        @Override
        public void execute(StackFrame frame) {
            long v1 = (long) frame.operationStack.pop();
            long v2 = (long) frame.operationStack.pop();
            long result = v1 & v2;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x7f";
        }
    };

    @Override
    public void fetchOperands(BytecodeReader reader) {

    }
}
