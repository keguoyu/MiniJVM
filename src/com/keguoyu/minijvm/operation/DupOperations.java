package com.keguoyu.minijvm.operation;

import com.keguoyu.minijvm.lang.BytecodeReader;
import com.keguoyu.minijvm.runtime.data.StackFrame;

public enum DupOperations implements Operation{

    //0x59
    DUP {
        @Override
        public void execute(StackFrame frame) {
            Object pop = frame.operationStack.pop();
            frame.operationStack.push(pop);
            frame.operationStack.push(pop);
        }

        @Override
        public String getCode() {
            return "0x59";
        }
    },

    //0x5a
    DUP_X1 {
        @Override
        public void execute(StackFrame frame) {
            Object pop1 = frame.operationStack.pop();
            Object pop2 = frame.operationStack.pop();
            frame.operationStack.push(pop1);
            frame.operationStack.push(pop2);
            frame.operationStack.push(pop1);
        }

        @Override
        public String getCode() {
            return "0x5a";
        }
    },

    //0x5b
    DUP_X2 {
        @Override
        public void execute(StackFrame frame) {
            Object pop1 = frame.operationStack.pop();
            Object pop2 = frame.operationStack.pop();
            Object pop3 = frame.operationStack.pop();
            frame.operationStack.push(pop1);
            frame.operationStack.push(pop2);
            frame.operationStack.push(pop3);
            frame.operationStack.push(pop1);
        }

        @Override
        public String getCode() {
            return "0x5b";
        }
    },

    //0x5c
    DUP2 {
        @Override
        public void execute(StackFrame frame) {
            Object pop1 = frame.operationStack.pop();
            Object pop2 = frame.operationStack.pop();
            frame.operationStack.push(pop2);
            frame.operationStack.push(pop1);
            frame.operationStack.push(pop2);
            frame.operationStack.push(pop1);
        }

        @Override
        public String getCode() {
            return "0x5c";
        }
    },

    //0x5d
    DUP2_X1 {
        @Override
        public void execute(StackFrame frame) {
            Object pop1 = frame.operationStack.pop();
            Object pop2 = frame.operationStack.pop();
            Object pop3 = frame.operationStack.pop();
            frame.operationStack.push(pop2);
            frame.operationStack.push(pop1);
            frame.operationStack.push(pop3);
            frame.operationStack.push(pop2);
            frame.operationStack.push(pop1);
        }

        @Override
        public String getCode() {
            return "0x5d";
        }
    },

    //0x5e
    DUP2_X2 {
        @Override
        public void execute(StackFrame frame) {
            Object pop1 = frame.operationStack.pop();
            Object pop2 = frame.operationStack.pop();
            Object pop3 = frame.operationStack.pop();
            Object pop4 = frame.operationStack.pop();
            frame.operationStack.push(pop2);
            frame.operationStack.push(pop1);
            frame.operationStack.push(pop4);
            frame.operationStack.push(pop3);
            frame.operationStack.push(pop2);
            frame.operationStack.push(pop1);
        }

        @Override
        public String getCode() {
            return "0x5e";
        }
    };

    @Override
    public void fetchOperands(BytecodeReader reader) {

    }
}
