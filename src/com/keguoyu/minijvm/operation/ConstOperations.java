package com.keguoyu.minijvm.operation;

import com.keguoyu.minijvm.lang.BytecodeReader;
import com.keguoyu.minijvm.runtime.data.StackFrame;
import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;

/**
 * 常量操作指令
 */
public enum ConstOperations implements Operation {

    //0×00
    NOP {

        @Override
        public void execute(StackFrame frame) {

        }
    },

    //0×01
    ACONST_NULL {

        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.push(null);
        }
    },

    //0×02
    ICONST_M1 {
        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.push(-1);
        }
    },

    //0×03
    ICONST_0 {

        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.push(0);
        }
    },

    //0×04
    ICONST_1 {
        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.push(1);
        }
    },

    //0×05
    ICONST_2 {
        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.push(2);
        }
    },

    //0×06
    ICONST_3 {
        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.push(3);
        }
    },

    //0×07
    ICONST_4 {
        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.push(4);
        }
    },

    //0×08
    ICONST_5 {
        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.push(5);
        }
    },

    //0×09
    LCONST_0 {
        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.push(0L);
        }
    },

    //0x0a
    LCONST_1 {
        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.push(1L);
        }
    },

    //0x0b
    FCONST_0 {
        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.push(0.0f);
        }
    },

    //0x0c
    FCONST_1 {
        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.push(1.0f);
        }
    },

    //0x0d
    FCONST_2 {
        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.push(2.0f);
        }
    },

    //0x0e
    DCONST_0 {
        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.push(0.0);
        }
    },

    //0x0f
    DCONST_1 {
        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.push(1.0);
        }
    };


    @Override
    public void fetchOperands(BytecodeReader reader) {
        //nothing to do
    }
}
