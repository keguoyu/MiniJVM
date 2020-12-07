package com.keguoyu.minijvm.operation;

import com.keguoyu.minijvm.lang.BytecodeReader;
import com.keguoyu.minijvm.runtime.data.StackFrame;

import java.util.Objects;

public enum LoadOperations implements Operation {

    //0x15
    ILOAD {

        int val;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            val = reader.readByte();
        }

        @Override
        public void execute(StackFrame frame) {
            iload(frame, val);
        }
    },

    //0x16
    LLOAD {
        int val;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            val = reader.readByte();
        }

        @Override
        public void execute(StackFrame frame) {
            lload(frame, val);
        }
    },

    //0x17
    FLOAD {

        int val;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            val = reader.readByte();
        }

        @Override
        public void execute(StackFrame frame) {
            fload(frame, val);
        }
    },

    //0x18
    DLOAD {
        int val;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            val = reader.readByte();
        }

        @Override
        public void execute(StackFrame frame) {
            dload(frame, val);
        }
    },

    //0x19
    ALOAD {
        int val;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            val = reader.readByte();
        }

        @Override
        public void execute(StackFrame frame) {
            aload(frame, val);
        }
    },

    //0x1a
    ILOAD_0 {

        @Override
        public void execute(StackFrame frame) {
            fload(frame, 0);
        }
    },

    //0x1b
    ILOAD_1 {
        @Override
        public void execute(StackFrame frame) {
            iload(frame, 1);
        }
    },

    //0x1c
    ILOAD_2 {
        @Override
        public void execute(StackFrame frame) {
            iload(frame, 2);
        }
    },

    //0x1d
    ILOAD_3 {
        @Override
        public void execute(StackFrame frame) {
            iload(frame, 3);
        }
    },

    //0x1e
    LLOAD_0 {

        @Override
        public void execute(StackFrame frame) {
            lload(frame, 0);
        }
    },

    //0x1f
    LLOAD_1 {
        @Override
        public void execute(StackFrame frame) {
            lload(frame, 1);
        }
    },

    //0x20
    LLOAD_2 {
        @Override
        public void execute(StackFrame frame) {
            lload(frame, 2);
        }
    },

    //0x21
    LLOAD_3 {
        @Override
        public void execute(StackFrame frame) {
            lload(frame, 3);
        }
    },

    //0x22
    FLOAD_0 {

        @Override
        public void execute(StackFrame frame) {
            fload(frame, 0);
        }
    },

    //0x23
    FLOAD_1 {
        @Override
        public void execute(StackFrame frame) {
            fload(frame, 1);
        }
    },

    //0x24
    FLOAD_2 {
        @Override
        public void execute(StackFrame frame) {
            fload(frame, 2);
        }
    },

    //0x25
    FLOAD_3 {
        @Override
        public void execute(StackFrame frame) {
            fload(frame, 3);
        }
    },

    //0x26
    DLOAD_0 {

        @Override
        public void execute(StackFrame frame) {
            dload(frame, 0);
        }
    },

    //0x27
    DLOAD_1 {
        @Override
        public void execute(StackFrame frame) {
            dload(frame, 1);
        }
    },

    //0x28
    DLOAD_2 {
        @Override
        public void execute(StackFrame frame) {
            dload(frame, 2);
        }
    },

    //0x29
    DLOAD_3 {
        @Override
        public void execute(StackFrame frame) {
            dload(frame, 3);
        }
    },

    //0x2a
    ALOAD_0 {

        @Override
        public void execute(StackFrame frame) {
            aload(frame, 0);
        }
    },

    //0x2b
    ALOAD_1 {
        @Override
        public void execute(StackFrame frame) {
            aload(frame, 1);
        }
    },

    //0x2c
    ALOAD_2 {
        @Override
        public void execute(StackFrame frame) {
            aload(frame, 2);
        }
    },

    //0x2d
    ALOAD_3 {
        @Override
        public void execute(StackFrame frame) {
            aload(frame, 3);
        }
    },

    //0x2e
    IALOAD {
        @Override
        public void execute(StackFrame frame) {
            int index = (int) frame.operationStack.pop();
            int[] iArray = (int[]) frame.operationStack.pop();
            Objects.requireNonNull(iArray);
            checkIndex(iArray.length, index);
            frame.operationStack.push(iArray[index]);
        }
    },

    //0x2f
    LALOAD {
        @Override
        public void execute(StackFrame frame) {
            int index = (int) frame.operationStack.pop();
            long[] iArray = (long[]) frame.operationStack.pop();
            Objects.requireNonNull(iArray);
            checkIndex(iArray.length, index);
            frame.operationStack.push(iArray[index]);
        }
    },

    //0x30
    FALOAD {
        @Override
        public void execute(StackFrame frame) {
            int index = (int) frame.operationStack.pop();
            float[] iArray = (float[]) frame.operationStack.pop();
            Objects.requireNonNull(iArray);
            checkIndex(iArray.length, index);
            frame.operationStack.push(iArray[index]);
        }
    },

    //0x31
    DALOAD {
        @Override
        public void execute(StackFrame frame) {
            int index = (int) frame.operationStack.pop();
            double[] iArray = (double[]) frame.operationStack.pop();
            Objects.requireNonNull(iArray);
            checkIndex(iArray.length, index);
            frame.operationStack.push(iArray[index]);
        }
    },

    //0x32
    AALOAD {
        @Override
        public void execute(StackFrame frame) {
            int index = (int) frame.operationStack.pop();
            Object[] iArray = (Object[]) frame.operationStack.pop();
            Objects.requireNonNull(iArray);
            checkIndex(iArray.length, index);
            frame.operationStack.push(iArray[index]);
        }
    },

    //0x33
    BALOAD {
        @Override
        public void execute(StackFrame frame) {
            int index = (int) frame.operationStack.pop();
            byte[] iArray = (byte[]) frame.operationStack.pop();
            Objects.requireNonNull(iArray);
            checkIndex(iArray.length, index);
            frame.operationStack.push(iArray[index]);
        }
    },

    //0x34
    CALOAD {
        @Override
        public void execute(StackFrame frame) {
            int index = (int) frame.operationStack.pop();
            char[] iArray = (char[]) frame.operationStack.pop();
            Objects.requireNonNull(iArray);
            checkIndex(iArray.length, index);
            frame.operationStack.push(iArray[index]);
        }
    },

    //0x35
    SALOAD {
        @Override
        public void execute(StackFrame frame) {
            int index = (int) frame.operationStack.pop();
            short[] iArray = (short[]) frame.operationStack.pop();
            Objects.requireNonNull(iArray);
            checkIndex(iArray.length, index);
            frame.operationStack.push(iArray[index]);
        }
    };

    public void checkIndex(int arrayLength, int index) {
        if (index >= arrayLength) {
            throw new ArrayIndexOutOfBoundsException(String.format("%1s > %2s", index, arrayLength));
        }
    }

    public void iload(StackFrame frame, int index) {
        int val = (int) frame.localVariable.getObject(index);
        frame.operationStack.push(val);
    }

    public void lload(StackFrame frame, int index) {
        long val = (long) frame.localVariable.getObject(index);
        frame.operationStack.push(val);
    }

    public void fload(StackFrame frame, int index) {
        float val = (float) frame.localVariable.getObject(index);
        frame.operationStack.push(val);
    }

    public void dload(StackFrame frame, int index) {
        double val = (double) frame.localVariable.getObject(index);
        frame.operationStack.push(val);
    }

    public void aload(StackFrame frame, int index) {
        Object val = frame.localVariable.getObject(index);
        frame.operationStack.push(val);
    }

    @Override
    public void fetchOperands(BytecodeReader reader) {
        //pass
    }
}
