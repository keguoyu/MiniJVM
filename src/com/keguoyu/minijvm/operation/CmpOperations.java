package com.keguoyu.minijvm.operation;

import com.keguoyu.minijvm.lang.BytecodeReader;
import com.keguoyu.minijvm.runtime.data.StackFrame;

public enum CmpOperations implements Operation {

    //0x94
    LCMP {
        @Override
        public void execute(StackFrame frame) {
            long v1 = (long) frame.operationStack.pop();
            long v2 = (long) frame.operationStack.pop();
            int result = Long.compare(v1, v2);
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x94";
        }
    },

    //0x95
    FCMPL {
        @Override
        public void execute(StackFrame frame) {
            try {
                float v1 = (float) frame.operationStack.pop();
                float v2 = (float) frame.operationStack.pop();
                int result = Float.compare(v1, v2);
                frame.operationStack.push(result);
            } catch (Exception exception) {
                frame.operationStack.push(-1);
            }
        }

        @Override
        public String getCode() {
            return "0x95";
        }
    },

    //0x96
    FCMPG {
        @Override
        public void execute(StackFrame frame) {
            try {
                float v1 = (float) frame.operationStack.pop();
                float v2 = (float) frame.operationStack.pop();
                int result = Float.compare(v1, v2);
                frame.operationStack.push(result);
            } catch (Exception exception) {
                frame.operationStack.push(1);
            }
        }

        @Override
        public String getCode() {
            return "0x96";
        }
    },

    //0x97
    DCMPL {
        @Override
        public void execute(StackFrame frame) {
            try {
                float v1 = (float) frame.operationStack.pop();
                float v2 = (float) frame.operationStack.pop();
                int result = Float.compare(v1, v2);
                frame.operationStack.push(result);
            } catch (Exception exception) {
                frame.operationStack.push(-1);
            }
        }

        @Override
        public String getCode() {
            return "0x97";
        }
    },

    //0x98
    DCMPG {
        @Override
        public void execute(StackFrame frame) {
            try {
                float v1 = (float) frame.operationStack.pop();
                float v2 = (float) frame.operationStack.pop();
                int result = Float.compare(v1, v2);
                frame.operationStack.push(result);
            } catch (Exception exception) {
                frame.operationStack.push(1);
            }
        }

        @Override
        public String getCode() {
            return "0x98";
        }
    }
    ;

    @Override
    public void fetchOperands(BytecodeReader reader) {

    }
}
