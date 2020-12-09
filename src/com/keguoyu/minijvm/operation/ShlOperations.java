package com.keguoyu.minijvm.operation;

import com.keguoyu.minijvm.lang.BytecodeReader;
import com.keguoyu.minijvm.runtime.data.StackFrame;

public enum ShlOperations implements Operation{

    //0x78
    ISHL {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            int v2 = (int) frame.operationStack.pop();
            int s = v1 & 0x1f;
            int result = v2 << s;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x78";
        }
    },

    //0x79
    LSHL {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            long v2 = (long) frame.operationStack.pop();
            int s = v1 & 0x3f;
            long result = v2 << s;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x79";
        }
    },

    //0x7a
    ISHR {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            int v2 = (int) frame.operationStack.pop();
            int s = v1 & 0x1f;
            int result = v2 >> s;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x7a";
        }
    },

    //0x7b
    LSHR {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            long v2 = (long) frame.operationStack.pop();
            int s = v1 & 0x3f;
            long result = v2 >> s;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x7b";
        }
    },

    //0x7c
    IUSHR {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            int v2 = (int) frame.operationStack.pop();
            int s = v1 & 0x1f;
            int result = v2 >> s;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x7c";
        }
    },

    //0x7d
    LUSHR {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            long v2 = (long) frame.operationStack.pop();
            int s = v1 & 0x3f;
            long result = v2 >> s;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x7d";
        }
    },

    ;


    @Override
    public void fetchOperands(BytecodeReader reader) {

    }
}
