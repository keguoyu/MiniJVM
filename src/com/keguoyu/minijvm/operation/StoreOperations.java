package com.keguoyu.minijvm.operation;

import com.keguoyu.minijvm.lang.BytecodeReader;
import com.keguoyu.minijvm.runtime.data.StackFrame;

import java.util.Arrays;
import java.util.Objects;

public enum StoreOperations implements Operation {

    //0x36
    ISTORE {

        int val;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            val = reader.readByte();
        }

        @Override
        public void execute(StackFrame frame) {
            istore(frame, val);
        }
    },

    //0x37
    LSTORE {

        int val;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            val = reader.readByte();
        }

        @Override
        public void execute(StackFrame frame) {
            lstore(frame, val);
        }
    },

    //0x38
    FSTORE {

        int val;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            val = reader.readByte();
        }

        @Override
        public void execute(StackFrame frame) {
            fstore(frame, val);
        }
    },

    //0x39
    DSTORE {

        int val;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            val = reader.readByte();
        }

        @Override
        public void execute(StackFrame frame) {
            dstore(frame, val);
        }
    },

    //0x3a
    ASTORE {
        int val;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            val = reader.readByte();
        }

        @Override
        public void execute(StackFrame frame) {
            astore(frame, val);
        }
    },

    //0x3b
    ISTORE_0 {
        @Override
        public void execute(StackFrame frame) {
            istore(frame, 0);
        }
    },

    //0x3c
    ISTORE_1 {
        @Override
        public void execute(StackFrame frame) {
            istore(frame, 1);
        }
    },

    //0x3d
    ISTORE_2 {
        @Override
        public void execute(StackFrame frame) {
            istore(frame, 2);
        }
    },

    //0x3e
    ISTORE_3 {
        @Override
        public void execute(StackFrame frame) {
            istore(frame, 3);
        }
    },

    //0x3f
    LSTORE_0 {
        @Override
        public void execute(StackFrame frame) {
            lstore(frame, 0);
        }
    },

    //0x40
    LSTORE_1 {
        @Override
        public void execute(StackFrame frame) {
            lstore(frame, 1);
        }
    },

    //0x41
    LSTORE_2 {
        @Override
        public void execute(StackFrame frame) {
            lstore(frame, 2);
        }
    },

    //0x42
    LSTORE_3 {
        @Override
        public void execute(StackFrame frame) {
            lstore(frame, 3);
        }
    },

    //0x43
    FSTORE_0 {
        @Override
        public void execute(StackFrame frame) {
            fstore(frame, 0);
        }
    },

    //0x44
    FSTORE_1 {
        @Override
        public void execute(StackFrame frame) {
            fstore(frame, 1);
        }
    },

    //0x45
    FSTORE_2 {
        @Override
        public void execute(StackFrame frame) {
            fstore(frame, 2);
        }
    },

    //0x46
    FSTORE_3 {
        @Override
        public void execute(StackFrame frame) {
            fstore(frame, 3);
        }
    },

    //0x47
    DSTORE_0 {
        @Override
        public void execute(StackFrame frame) {
            dstore(frame, 0);
        }
    },

    //0x48
    DSTORE_1 {
        @Override
        public void execute(StackFrame frame) {
            dstore(frame, 1);
        }
    },

    //0x49
    DSTORE_2 {
        @Override
        public void execute(StackFrame frame) {
            dstore(frame, 2);
        }
    },

    //0x4a
    DSTORE_3 {
        @Override
        public void execute(StackFrame frame) {
            dstore(frame, 3);
        }
    },

    //0x4b
    ASTORE_0 {
        @Override
        public void execute(StackFrame frame) {
            astore(frame, 0);
        }
    },

    //0x4c
    ASTORE_1 {
        @Override
        public void execute(StackFrame frame) {
            astore(frame, 1);
        }
    },

    //0x4d
    ASTORE_2 {
        @Override
        public void execute(StackFrame frame) {
            astore(frame, 2);
        }
    },

    //0x4e
    ASTORE_3 {
        @Override
        public void execute(StackFrame frame) {
            astore(frame, 3);
        }
    },

    //0x4f
    IASTORE {
        @Override
        public void execute(StackFrame frame) {
            int value = (int) frame.operationStack.pop();
            int index = (int) frame.operationStack.pop();
            int[] array = (int[]) frame.operationStack.pop();
            Objects.requireNonNull(array);
            checkIndex(array.length, index);
            array[index] = value;
        }
    },

    //0x50
    LASTORE {
        @Override
        public void execute(StackFrame frame) {
            int value = (int) frame.operationStack.pop();
            int index = (int) frame.operationStack.pop();
            long[] array = (long[]) frame.operationStack.pop();
            Objects.requireNonNull(array);
            checkIndex(array.length, index);
            array[index] = value;
        }
    },

    //0x51
    FASTORE {
        @Override
        public void execute(StackFrame frame) {
            int value = (int) frame.operationStack.pop();
            int index = (int) frame.operationStack.pop();
            float[] array = (float[]) frame.operationStack.pop();
            Objects.requireNonNull(array);
            checkIndex(array.length, index);
            array[index] = value;
        }
    },

    //0x52
    DASTORE {
        @Override
        public void execute(StackFrame frame) {
            int value = (int) frame.operationStack.pop();
            int index = (int) frame.operationStack.pop();
            double[] array = (double[]) frame.operationStack.pop();
            Objects.requireNonNull(array);
            checkIndex(array.length, index);
            array[index] = value;
        }
    },

    //0x53
    AASTORE {
        @Override
        public void execute(StackFrame frame) {
            int value = (int) frame.operationStack.pop();
            int index = (int) frame.operationStack.pop();
            Object[] array = (Object[]) frame.operationStack.pop();
            Objects.requireNonNull(array);
            checkIndex(array.length, index);
            array[index] = value;
        }
    },

    //0x54
    BASTORE {
        @Override
        public void execute(StackFrame frame) {
            int value = (int) frame.operationStack.pop();
            int index = (int) frame.operationStack.pop();
            byte[] array = (byte[]) frame.operationStack.pop();
            Objects.requireNonNull(array);
            checkIndex(array.length, index);
            array[index] = (byte) value;
        }
    },

    //0x55
    CASTORE {
        @Override
        public void execute(StackFrame frame) {
            int value = (int) frame.operationStack.pop();
            int index = (int) frame.operationStack.pop();
            char[] array = (char[]) frame.operationStack.pop();
            Objects.requireNonNull(array);
            checkIndex(array.length, index);
            array[index] = (char) value;
        }
    },

    //0x56
    SASTORE {
        @Override
        public void execute(StackFrame frame) {
            int value = (int) frame.operationStack.pop();
            int index = (int) frame.operationStack.pop();
            short[] array = (short[]) frame.operationStack.pop();
            Objects.requireNonNull(array);
            checkIndex(array.length, index);
            array[index] = (short) value;
        }
    };

    public void checkIndex(int arrayLength, int index) {
        if (index >= arrayLength) {
            throw new ArrayIndexOutOfBoundsException(String.format("%1s > %2s", index, arrayLength));
        }
    }

    public void istore(StackFrame frame, int index) {
        int val = (int) frame.operationStack.pop();
        frame.localVariable.set(index, val);
    }

    public void lstore(StackFrame frame, int index) {
        long val = (long) frame.operationStack.pop();
        frame.localVariable.set(index, val);
    }

    public void fstore(StackFrame frame, int index) {
        float val = (float) frame.operationStack.pop();
        frame.localVariable.set(index, val);
    }

    public void dstore(StackFrame frame, int index) {
        double val = (double) frame.operationStack.pop();
        frame.localVariable.set(index, val);
    }

    public void astore(StackFrame frame, int index) {
        Object val = frame.operationStack.pop();
        frame.localVariable.set(index, val);
    }


    @Override
    public void fetchOperands(BytecodeReader reader) {
        //do nothing
    }
}
