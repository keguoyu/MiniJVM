package com.keguoyu.minijvm.operation;

import com.keguoyu.minijvm.lang.BytecodeReader;
import com.keguoyu.minijvm.main.JavaVirtualMachine;
import com.keguoyu.minijvm.runtime.SingleConstantPool;
import com.keguoyu.minijvm.runtime.data.StackFrame;

public enum LdcOperations implements Operation {

    //0×12
    LDC {

        byte index;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            index = reader.readByte();
        }

        @Override
        public void execute(StackFrame frame) {
            ldc(frame, index);
        }

        @Override
        public String getCode() {
            return "0x12";
        }
    },

    //0x13
    LDC_W {

        short index;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            index = reader.readShort();
        }

        @Override
        public void execute(StackFrame frame) {
            ldc(frame, index);
        }

        @Override
        public String getCode() {
            return "0x13";
        }
    },

    //0x14
    LDC2_W {
        short index;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            index = reader.readShort();
        }

        @Override
        public void execute(StackFrame frame) {
            ldc(frame, index);
        }

        @Override
        public String getCode() {
            return "0x14";
        }
    };

    public void ldc(StackFrame stackFrame, int index) {
        SingleConstantPool constantPool = JavaVirtualMachine.getMethodArea()
                .getConstantPool(stackFrame.jvmMethod.jvmClass);
        Object constant = constantPool.get(index);
        stackFrame.operationStack.push(constant);
    }

}
