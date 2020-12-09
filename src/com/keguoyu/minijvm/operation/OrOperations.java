package com.keguoyu.minijvm.operation;

import com.keguoyu.minijvm.lang.BytecodeReader;
import com.keguoyu.minijvm.runtime.data.StackFrame;

public enum OrOperations implements Operation {

    //0x80
    IOR {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            int v2 = (int) frame.operationStack.pop();
            int result = v1 | v2;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x80";
        }
    },

    //0x81
    LOR {
        @Override
        public void execute(StackFrame frame) {
            long v1 = (long) frame.operationStack.pop();
            long v2 = (long) frame.operationStack.pop();
            long result = v1 | v2;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x81";
        }
    },

    //0x82
    IXOR {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            int v2 = (int) frame.operationStack.pop();
            int result = v1 ^ v2;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x82";
        }
    },

    //0x83
    LXOR {
        @Override
        public void execute(StackFrame frame) {
            long v1 = (long) frame.operationStack.pop();
            long v2 = (long) frame.operationStack.pop();
            long result = v1 ^ v2;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x83";
        }
    }
    ;

    @Override
    public void fetchOperands(BytecodeReader reader) {

    }
}
