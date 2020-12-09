package com.keguoyu.minijvm.operation;

import com.keguoyu.minijvm.lang.BytecodeReader;
import com.keguoyu.minijvm.runtime.data.StackFrame;
import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;

/**
 * 常量操作指令
 */
public enum ConstOperations implements Operation {

    //0x00
    NOP {

        @Override
        public void execute(StackFrame frame) {

        }

        @Override
        public String getCode() {
            return "0x00";
        }
    },

    //0x01
    ACONST_NULL {

        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.push(null);
        }

        @Override
        public String getCode() {
            return "0x01";
        }
    },

    //0×02
    ICONST_M1 {
        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.push(-1);
        }

        @Override
        public String getCode() {
            return "0x02";
        }
    },

    //0×03
    ICONST_0 {

        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.push(0);
        }

        @Override
        public String getCode() {
            return "0x03";
        }
    },

    //0×04
    ICONST_1 {
        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.push(1);
        }

        @Override
        public String getCode() {
            return "0x04";
        }
    },

    //0×05
    ICONST_2 {
        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.push(2);
        }

        @Override
        public String getCode() {
            return "0x05";
        }
    },

    //0×06
    ICONST_3 {
        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.push(3);
        }

        @Override
        public String getCode() {
            return "0x06";
        }
    },

    //0×07
    ICONST_4 {
        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.push(4);
        }

        @Override
        public String getCode() {
            return "0x07";
        }
    },

    //0×08
    ICONST_5 {
        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.push(5);
        }

        @Override
        public String getCode() {
            return "0x08";
        }
    },

    //0×09
    LCONST_0 {
        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.push(0L);
        }

        @Override
        public String getCode() {
            return "0x09";
        }
    },

    //0x0a
    LCONST_1 {
        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.push(1L);
        }

        @Override
        public String getCode() {
            return "0x0a";
        }
    },

    //0x0b
    FCONST_0 {
        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.push(0.0f);
        }

        @Override
        public String getCode() {
            return "0x0b";
        }
    },

    //0x0c
    FCONST_1 {
        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.push(1.0f);
        }

        @Override
        public String getCode() {
            return "0x0c";
        }
    },

    //0x0d
    FCONST_2 {
        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.push(2.0f);
        }

        @Override
        public String getCode() {
            return "0x0d";
        }
    },

    //0x0e
    DCONST_0 {
        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.push(0.0);
        }

        @Override
        public String getCode() {
            return "0x0e";
        }
    },

    //0x0f
    DCONST_1 {
        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.push(1.0);
        }

        @Override
        public String getCode() {
            return "0x0f";
        }
    };


    @Override
    public void fetchOperands(BytecodeReader reader) {
        //nothing to do
    }
}
