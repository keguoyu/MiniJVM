package com.keguoyu.minijvm.operation;

import com.keguoyu.minijvm.lang.BytecodeReader;
import com.keguoyu.minijvm.runtime.data.StackFrame;

public enum GotoOperations implements Operation {

    GOTO {

        short offset;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            offset = reader.readShort();
        }

        @Override
        public void execute(StackFrame frame) {
            int pc = frame.getJvmThread().getPc();
            pc = pc + offset;
            frame.setNextPC(pc);
        }

        @Override
        public String getCode() {
            return "0xa7";
        }
    }

}
