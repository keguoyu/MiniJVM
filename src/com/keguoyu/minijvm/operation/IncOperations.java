package com.keguoyu.minijvm.operation;

import com.keguoyu.minijvm.lang.BytecodeReader;
import com.keguoyu.minijvm.runtime.data.StackFrame;

public enum IncOperations implements Operation{

    INC {

        byte index;
        int cons;
        @Override
        public void fetchOperands(BytecodeReader reader) {
            index = reader.readByte();
            cons = reader.readByte();
        }

        @Override
        public void execute(StackFrame frame) {
            int value = (int) frame.localVariable.get(index);
            value += cons;
            frame.localVariable.set(index, value);
        }
    }

}
