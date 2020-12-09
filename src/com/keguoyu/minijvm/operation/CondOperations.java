package com.keguoyu.minijvm.operation;

import com.keguoyu.minijvm.lang.BytecodeReader;
import com.keguoyu.minijvm.runtime.data.StackFrame;

public enum CondOperations implements Operation {

    //0x99
    IFEQ {
        @Override
        public void execute(StackFrame frame) {
           int v1 = (int) frame.operationStack.pop();
           if (v1 == 0) {
               go(frame);
           }
        }

        @Override
        public String getCode() {
            return "0x99";
        }
    },

    //0x9a
    IFNE {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            if (v1 != 0) {
                go(frame);
            }
        }

        @Override
        public String getCode() {
            return "0x9a";
        }
    },

    //0x9b
    IFLT {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            if (v1 < 0) {
                go(frame);
            }
        }

        @Override
        public String getCode() {
            return "0x9b";
        }
    },

    //0x9c
    IFGE {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            if (v1 >= 0) {
                go(frame);
            }
        }

        @Override
        public String getCode() {
            return "0x9c";
        }
    },

    //0x9d
    IFGT {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            if (v1 > 0) {
                go(frame);
            }
        }

        @Override
        public String getCode() {
            return "0x9d";
        }
    },

    //0x9e
    IFLE {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            if (v1 <= 0) {
                go(frame);
            }
        }

        @Override
        public String getCode() {
            return "0x9e";
        }
    },

    //0x9f
    IF_ICMPEQ {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            int v2 = (int) frame.operationStack.pop();
            if (v1 == v2) {
                go(frame);
            }
        }

        @Override
        public String getCode() {
            return "0x9f";
        }
    },

    //0xa0
    IF_ICMPNE {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            int v2 = (int) frame.operationStack.pop();
            if (v1 != v2) {
                go(frame);
            }
        }

        @Override
        public String getCode() {
            return "0xa0";
        }
    },

    //0xa1
    IF_ICMPLT {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            int v2 = (int) frame.operationStack.pop();
            if (v1 > v2) {
                go(frame);
            }
        }

        @Override
        public String getCode() {
            return "0xa1";
        }
    },

    //0xa2
    IF_ICMPGE {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            int v2 = (int) frame.operationStack.pop();
            if (v1 <= v2) {
                go(frame);
            }
        }

        @Override
        public String getCode() {
            return "0xa2";
        }
    },

    //0xa3
    IF_ICMPGT {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            int v2 = (int) frame.operationStack.pop();
            if (v1 < v2) {
                go(frame);
            }
        }

        @Override
        public String getCode() {
            return "0xa3";
        }
    },

    //0xa4
    IF_ICMPLE {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            int v2 = (int) frame.operationStack.pop();
            if (v1 >= v2) {
                go(frame);
            }
        }

        @Override
        public String getCode() {
            return "0xa4";
        }
    },

    //0xa5
    IF_ACMPEQ {
        @Override
        public void execute(StackFrame frame) {
            Object v1 = frame.operationStack.pop();
            Object v2 = frame.operationStack.pop();
            if (v1 == v2) {
                go(frame);
            }
        }

        @Override
        public String getCode() {
            return "0xa5";
        }
    },

    //0xa6
    IF_ACMPNE {
        @Override
        public void execute(StackFrame frame) {
            Object v1 = frame.operationStack.pop();
            Object v2 = frame.operationStack.pop();
            if (v1 != v2) {
                go(frame);
            }
        }

        @Override
        public String getCode() {
            return "0xa6";
        }
    }

    ;
    public int offset;
    
    public void go(StackFrame frame) {
        Goto.GOTO.branch(frame, offset);
    }
    
    @Override
    public void fetchOperands(BytecodeReader reader) {
        offset = reader.readShort();
    }
}
