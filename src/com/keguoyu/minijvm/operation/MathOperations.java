package com.keguoyu.minijvm.operation;

import com.keguoyu.minijvm.lang.BytecodeReader;
import com.keguoyu.minijvm.runtime.data.StackFrame;

public enum MathOperations implements Operation {

    //0x60
    IADD {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            int v2 = (int) frame.operationStack.pop();
            int result = v1 + v2;
            frame.operationStack.push(result);
        }
    },

    //0x61
    LADD {
        @Override
        public void execute(StackFrame frame) {
            long v1 = (long) frame.operationStack.pop();
            long v2 = (long) frame.operationStack.pop();
            long result = v1 + v2;
            frame.operationStack.push(result);
        }
    },

    //0x62
    FADD {
        @Override
        public void execute(StackFrame frame) {
            float v1 = (float) frame.operationStack.pop();
            float v2 = (float) frame.operationStack.pop();
            float result = v1 + v2;
            frame.operationStack.push(result);
        }
    },

    //0x63
    DADD {
        @Override
        public void execute(StackFrame frame) {
            double v1 = (double) frame.operationStack.pop();
            double v2 = (double) frame.operationStack.pop();
            double result = v1 + v2;
            frame.operationStack.push(result);
        }
    },

    //0x64
    ISUB {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            int v2 = (int) frame.operationStack.pop();
            int result = v2 - v1;
            frame.operationStack.push(result);
        }
    },

    //0x65
    LSUB {
        @Override
        public void execute(StackFrame frame) {
            long v1 = (long) frame.operationStack.pop();
            long v2 = (long) frame.operationStack.pop();
            long result = v2 - v1;
            frame.operationStack.push(result);
        }
    },

    //0x66
    FSUB {
        @Override
        public void execute(StackFrame frame) {
            float v1 = (float) frame.operationStack.pop();
            float v2 = (float) frame.operationStack.pop();
            float result = v2 - v1;
            frame.operationStack.push(result);
        }
    },

    //0x67
    DSUB {
        @Override
        public void execute(StackFrame frame) {
            double v1 = (double) frame.operationStack.pop();
            double v2 = (double) frame.operationStack.pop();
            double result = v2 - v1;
            frame.operationStack.push(result);
        }
    },

    //0x68
    IMUL {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            int v2 = (int) frame.operationStack.pop();
            int result = v1 * v2;
            frame.operationStack.push(result);
        }
    },

    //0x69
    LMUL {
        @Override
        public void execute(StackFrame frame) {
            long v1 = (long) frame.operationStack.pop();
            long v2 = (long) frame.operationStack.pop();
            long result = v1 * v2;
            frame.operationStack.push(result);
        }
    },

    //0x6a
    FMUL {
        @Override
        public void execute(StackFrame frame) {
            float v1 = (float) frame.operationStack.pop();
            float v2 = (float) frame.operationStack.pop();
            float result = v1 * v2;
            frame.operationStack.push(result);
        }
    },

    //0x6b
    DMUL {
        @Override
        public void execute(StackFrame frame) {
            double v1 = (double) frame.operationStack.pop();
            double v2 = (double) frame.operationStack.pop();
            double result = v1 * v2;
            frame.operationStack.push(result);
        }
    },

    //0x6c
    IDIV {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            int v2 = (int) frame.operationStack.pop();
            if (v1 == 0) {
                throw new RuntimeException("Div by 0");
            }
            int result = v2 / v1;
            frame.operationStack.push(result);
        }
    },

    //0x6d
    LDIV {
        @Override
        public void execute(StackFrame frame) {
            long v1 = (long) frame.operationStack.pop();
            long v2 = (long) frame.operationStack.pop();
            if (v1 == 0L) {
                throw new RuntimeException("Div by 0L");
            }
            long result = v2 / v1;
            frame.operationStack.push(result);
        }
    },

    //0x6e
    FDIV {
        @Override
        public void execute(StackFrame frame) {
            float v1 = (float) frame.operationStack.pop();
            float v2 = (float) frame.operationStack.pop();
            if (v1 == 0f) {
                throw new RuntimeException("Div by 0f");
            }
            float result = v2 / v1;
            frame.operationStack.push(result);
        }
    },

    //0x6f
    DDIV {
        @Override
        public void execute(StackFrame frame) {
            double v1 = (double) frame.operationStack.pop();
            double v2 = (double) frame.operationStack.pop();
            if (v1 == 0.0) {
                throw new RuntimeException("Div by 0.0");
            }
            double result = v2 / v1;
            frame.operationStack.push(result);
        }
    },

    //0x70
    IREM {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            int v2 = (int) frame.operationStack.pop();
            if (v1 == 0) {
                throw new RuntimeException("Rem by 0");
            }
            int result = v2 % v1;
            frame.operationStack.push(result);
        }
    },

    //0x71
    LREM {
        @Override
        public void execute(StackFrame frame) {
            long v1 = (long) frame.operationStack.pop();
            long v2 = (long) frame.operationStack.pop();
            if (v1 == 0L) {
                throw new RuntimeException("Rem by 0L");
            }
            long result = v2 % v1;
            frame.operationStack.push(result);
        }
    },

    //0x72
    FREM {
        @Override
        public void execute(StackFrame frame) {
            float v1 = (float) frame.operationStack.pop();
            float v2 = (float) frame.operationStack.pop();
            if (v1 == 0f) {
                throw new RuntimeException("Rem by 0f");
            }
            float result = v2 % v1;
            frame.operationStack.push(result);
        }
    },

    //0x73
    DREM {
        @Override
        public void execute(StackFrame frame) {
            double v1 = (double) frame.operationStack.pop();
            double v2 = (double) frame.operationStack.pop();
            if (v1 == 0.0) {
                throw new RuntimeException("Rem by 0.0");
            }
            double result = v2 % v1;
            frame.operationStack.push(result);
        }
    },

    //0x74
    INEG {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            frame.operationStack.push(-v1);
        }
    },

    //0x75
    LNEG {
        @Override
        public void execute(StackFrame frame) {
            long v1 = (long) frame.operationStack.pop();
            frame.operationStack.push(-v1);
        }
    },

    //0x76
    FNEG {
        @Override
        public void execute(StackFrame frame) {
            float v1 = (float) frame.operationStack.pop();
            frame.operationStack.push(-v1);
        }
    },

    //0x77
    DNEG {
        @Override
        public void execute(StackFrame frame) {
            double v1 = (double) frame.operationStack.pop();
            frame.operationStack.push(-v1);
        }
    };

    @Override
    public void fetchOperands(BytecodeReader reader) {
    }
}
