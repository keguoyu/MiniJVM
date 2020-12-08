package com.keguoyu.minijvm.operation;

import com.keguoyu.minijvm.lang.BytecodeReader;
import com.keguoyu.minijvm.runtime.data.StackFrame;

public enum CastOperations implements Operation {


    I2L {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            long result = (long)v1;
            frame.operationStack.push(result);
        }
    },

    I2F {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            float result = (float) v1;
            frame.operationStack.push(result);
        }
    },

    I2D {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            double result = (double) v1;
            frame.operationStack.push(result);
        }
    },

    L2I {
        @Override
        public void execute(StackFrame frame) {
            long v1 = (long) frame.operationStack.pop();
            int result = (int) v1;
            frame.operationStack.push(result );
        }
    },

    L2F {
        @Override
        public void execute(StackFrame frame) {
            long v1 = (long) frame.operationStack.pop();
            float result = (float) v1;
            frame.operationStack.push(result);
        }
    }
    ;

    @Override
    public void fetchOperands(BytecodeReader reader) {

    }
}
