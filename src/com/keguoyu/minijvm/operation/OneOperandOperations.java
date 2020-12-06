package com.keguoyu.minijvm.operation;

import com.keguoyu.minijvm.lang.BytecodeReader;
import com.keguoyu.minijvm.runtime.data.StackFrame;

public enum OneOperandOperations implements Operation {

    //0×10
    BI_PUSH() {

        byte val;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            val = reader.readByte();
        }

        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.push((int)val);
        }
    },

    //0×11
    SI_PUSH() {

        short val;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            val = reader.readShort();
        }

        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.push((int)val);
        }
    }

}
