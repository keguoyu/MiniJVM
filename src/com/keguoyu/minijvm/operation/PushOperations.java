package com.keguoyu.minijvm.operation;

import com.keguoyu.minijvm.lang.BytecodeReader;
import com.keguoyu.minijvm.runtime.data.StackFrame;

public enum PushOperations implements Operation {

    //0x10
    BI_PUSH {

        byte val;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            val = reader.readByte();
            System.out.println("BIPUSH " + (int)val);
        }

        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.push((int)val);
        }

        @Override
        public String getCode() {
            return "0x10";
        }
    },

    //0x11
    SI_PUSH {

        short val;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            val = reader.readShort();
        }

        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.push((int)val);
        }

        @Override
        public String getCode() {
            return "0x11";
        }
    }

}
