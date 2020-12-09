package com.keguoyu.minijvm.operation;

import com.keguoyu.minijvm.lang.BytecodeReader;
import com.keguoyu.minijvm.runtime.data.StackFrame;

public enum CastOperations implements Operation {

    //0x85
    I2L {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            long result = (long)v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x85";
        }
    },

    //0x86
    I2F {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            float result = (float) v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x86";
        }
    },

    //0x87
    I2D {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            double result = (double) v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x87";
        }
    },

    //0x88
    L2I {
        @Override
        public void execute(StackFrame frame) {
            long v1 = (long) frame.operationStack.pop();
            int result = (int) v1;
            frame.operationStack.push(result );
        }

        @Override
        public String getCode() {
            return "0x88";
        }
    },

    //0x89
    L2F {
        @Override
        public void execute(StackFrame frame) {
            long v1 = (long) frame.operationStack.pop();
            float result = (float) v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x89";
        }
    },

    //0x8a
    L2D {
        @Override
        public void execute(StackFrame frame) {
            long v1 = (long) frame.operationStack.pop();
            double result = (double) v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x8a";
        }
    },

    //0x8b
    F2I {
        @Override
        public void execute(StackFrame frame) {
            float v1 = (float) frame.operationStack.pop();
            int result = (int) v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x8b";
        }
    },

    //0x8c
    F2L {
        @Override
        public void execute(StackFrame frame) {
            float v1 = (float) frame.operationStack.pop();
            long result = (long) v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x8c";
        }
    },

    //0x8d
    F2D {
        @Override
        public void execute(StackFrame frame) {
            float v1 = (float) frame.operationStack.pop();
            double result = (double) v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x8d";
        }
    },

    //0x8e
    D2I {
        @Override
        public void execute(StackFrame frame) {
            double v1 = (double) frame.operationStack.pop();
            int result = (int) v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x8e";
        }
    },

    //0x8f
    D2L {
        @Override
        public void execute(StackFrame frame) {
          double v1 = (double) frame.operationStack.pop();
          long result = (long) v1;
          frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x8f";
        }
    },

    //0x90
    D2F {
        @Override
        public void execute(StackFrame frame) {
            double v1 = (double) frame.operationStack.pop();
            float result = (float) v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x90";
        }
    },

    //0x91
    I2B {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            byte result = (byte) v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x91";
        }
    },

    //0x92
    I2C {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            char result = (char) v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x92";
        }
    },

    //0x93
    I2S {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            short result = (short) v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x93";
        }
    }
    ;

    @Override
    public void fetchOperands(BytecodeReader reader) {

    }
}
