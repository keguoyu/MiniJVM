package com.keguoyu.minijvm.operation;

import com.keguoyu.minijvm.data.ref.ClassReference;
import com.keguoyu.minijvm.data.ref.FieldReference;
import com.keguoyu.minijvm.data.ref.MethodRef;
import com.keguoyu.minijvm.lang.*;
import com.keguoyu.minijvm.main.JavaVirtualMachine;
import com.keguoyu.minijvm.runtime.RuntimeConstantPool;
import com.keguoyu.minijvm.runtime.data.StackFrame;

import java.util.Objects;

/**
 * 所有指令集
 */
public enum OperationsGroup implements Operation {

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
    },


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
    },

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
    },

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

        @Override
        public String getCode() {
            return "0x15";
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

        @Override
        public String getCode() {
            return "0x16";
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

        @Override
        public String getCode() {
            return "0x17";
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

        @Override
        public String getCode() {
            return "0x18";
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

        @Override
        public String getCode() {
            return "0x19";
        }
    },

    //0x1a
    ILOAD_0 {

        @Override
        public void execute(StackFrame frame) {
            iload(frame, 0);
        }

        @Override
        public String getCode() {
            return "0x1a";
        }
    },

    //0x1b
    ILOAD_1 {
        @Override
        public void execute(StackFrame frame) {
            iload(frame, 1);
        }

        @Override
        public String getCode() {
            return "0x1b";
        }
    },

    //0x1c
    ILOAD_2 {
        @Override
        public void execute(StackFrame frame) {
            iload(frame, 2);
        }

        @Override
        public String getCode() {
            return "0x1c";
        }
    },

    //0x1d
    ILOAD_3 {
        @Override
        public void execute(StackFrame frame) {
            iload(frame, 3);
        }

        @Override
        public String getCode() {
            return "0x1d";
        }
    },

    //0x1e
    LLOAD_0 {

        @Override
        public void execute(StackFrame frame) {
            lload(frame, 0);
        }

        @Override
        public String getCode() {
            return "0x1e";
        }
    },

    //0x1f
    LLOAD_1 {
        @Override
        public void execute(StackFrame frame) {
            lload(frame, 1);
        }

        @Override
        public String getCode() {
            return "0x1f";
        }
    },

    //0x20
    LLOAD_2 {
        @Override
        public void execute(StackFrame frame) {
            lload(frame, 2);
        }

        @Override
        public String getCode() {
            return "0x20";
        }
    },

    //0x21
    LLOAD_3 {
        @Override
        public void execute(StackFrame frame) {
            lload(frame, 3);
        }

        @Override
        public String getCode() {
            return "0x21";
        }
    },

    //0x22
    FLOAD_0 {

        @Override
        public void execute(StackFrame frame) {
            fload(frame, 0);
        }

        @Override
        public String getCode() {
            return "0x22";
        }
    },

    //0x23
    FLOAD_1 {
        @Override
        public void execute(StackFrame frame) {
            fload(frame, 1);
        }

        @Override
        public String getCode() {
            return "0x23";
        }
    },

    //0x24
    FLOAD_2 {
        @Override
        public void execute(StackFrame frame) {
            fload(frame, 2);
        }

        @Override
        public String getCode() {
            return "0x24";
        }
    },

    //0x25
    FLOAD_3 {
        @Override
        public void execute(StackFrame frame) {
            fload(frame, 3);
        }

        @Override
        public String getCode() {
            return "0x25";
        }
    },

    //0x26
    DLOAD_0 {

        @Override
        public void execute(StackFrame frame) {
            dload(frame, 0);
        }

        @Override
        public String getCode() {
            return "0x26";
        }
    },

    //0x27
    DLOAD_1 {
        @Override
        public void execute(StackFrame frame) {
            dload(frame, 1);
        }

        @Override
        public String getCode() {
            return "0x27";
        }
    },

    //0x28
    DLOAD_2 {
        @Override
        public void execute(StackFrame frame) {
            dload(frame, 2);
        }

        @Override
        public String getCode() {
            return "0x28";
        }
    },

    //0x29
    DLOAD_3 {
        @Override
        public void execute(StackFrame frame) {
            dload(frame, 3);
        }

        @Override
        public String getCode() {
            return "0x29";
        }
    },

    //0x2a
    ALOAD_0 {

        @Override
        public void execute(StackFrame frame) {
            aload(frame, 0);
        }

        @Override
        public String getCode() {
            return "0x2a";
        }
    },

    //0x2b
    ALOAD_1 {
        @Override
        public void execute(StackFrame frame) {
            aload(frame, 1);
        }

        @Override
        public String getCode() {
            return "0x2b";
        }
    },

    //0x2c
    ALOAD_2 {
        @Override
        public void execute(StackFrame frame) {
            aload(frame, 2);
        }

        @Override
        public String getCode() {
            return "0x2c";
        }
    },

    //0x2d
    ALOAD_3 {
        @Override
        public void execute(StackFrame frame) {
            aload(frame, 3);
        }

        @Override
        public String getCode() {
            return "0x2d";
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

        @Override
        public String getCode() {
            return "0x2e";
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

        @Override
        public String getCode() {
            return "0x2f";
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

        @Override
        public String getCode() {
            return "0x30";
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

        @Override
        public String getCode() {
            return "0x31";
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

        @Override
        public String getCode() {
            return "0x32";
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

        @Override
        public String getCode() {
            return "0x33";
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

        @Override
        public String getCode() {
            return "0x34";
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

        @Override
        public String getCode() {
            return "0x35";
        }
    },

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

        @Override
        public String getCode() {
            return "0x36";
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

        @Override
        public String getCode() {
            return "0x37";
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

        @Override
        public String getCode() {
            return "0x38";
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

        @Override
        public String getCode() {
            return "0x39";
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

        @Override
        public String getCode() {
            return "0x3a";
        }
    },

    //0x3b
    ISTORE_0 {
        @Override
        public void execute(StackFrame frame) {
            istore(frame, 0);
        }

        @Override
        public String getCode() {
            return "0x3b";
        }
    },

    //0x3c
    ISTORE_1 {
        @Override
        public void execute(StackFrame frame) {
            istore(frame, 1);
        }

        @Override
        public String getCode() {
            return "0x3c";
        }
    },

    //0x3d
    ISTORE_2 {
        @Override
        public void execute(StackFrame frame) {
            istore(frame, 2);
        }

        @Override
        public String getCode() {
            return "0x3d";
        }
    },

    //0x3e
    ISTORE_3 {
        @Override
        public void execute(StackFrame frame) {
            istore(frame, 3);
        }

        @Override
        public String getCode() {
            return "0x3e";
        }
    },

    //0x3f
    LSTORE_0 {
        @Override
        public void execute(StackFrame frame) {
            lstore(frame, 0);
        }

        @Override
        public String getCode() {
            return "0x3f";
        }
    },

    //0x40
    LSTORE_1 {
        @Override
        public void execute(StackFrame frame) {
            lstore(frame, 1);
        }

        @Override
        public String getCode() {
            return "0x40";
        }
    },

    //0x41
    LSTORE_2 {
        @Override
        public void execute(StackFrame frame) {
            lstore(frame, 2);
        }

        @Override
        public String getCode() {
            return "0x41";
        }
    },

    //0x42
    LSTORE_3 {
        @Override
        public void execute(StackFrame frame) {
            lstore(frame, 3);
        }

        @Override
        public String getCode() {
            return "0x42";
        }
    },

    //0x43
    FSTORE_0 {
        @Override
        public void execute(StackFrame frame) {
            fstore(frame, 0);
        }

        @Override
        public String getCode() {
            return "0x43";
        }
    },

    //0x44
    FSTORE_1 {
        @Override
        public void execute(StackFrame frame) {
            fstore(frame, 1);
        }

        @Override
        public String getCode() {
            return "0x44";
        }
    },

    //0x45
    FSTORE_2 {
        @Override
        public void execute(StackFrame frame) {
            fstore(frame, 2);
        }

        @Override
        public String getCode() {
            return "0x45";
        }
    },

    //0x46
    FSTORE_3 {
        @Override
        public void execute(StackFrame frame) {
            fstore(frame, 3);
        }

        @Override
        public String getCode() {
            return "0x46";
        }
    },

    //0x47
    DSTORE_0 {
        @Override
        public void execute(StackFrame frame) {
            dstore(frame, 0);
        }

        @Override
        public String getCode() {
            return "0x47";
        }
    },

    //0x48
    DSTORE_1 {
        @Override
        public void execute(StackFrame frame) {
            dstore(frame, 1);
        }

        @Override
        public String getCode() {
            return "0x48";
        }
    },

    //0x49
    DSTORE_2 {
        @Override
        public void execute(StackFrame frame) {
            dstore(frame, 2);
        }

        @Override
        public String getCode() {
            return "0x49";
        }
    },

    //0x4a
    DSTORE_3 {
        @Override
        public void execute(StackFrame frame) {
            dstore(frame, 3);
        }

        @Override
        public String getCode() {
            return "0x4a";
        }
    },

    //0x4b
    ASTORE_0 {
        @Override
        public void execute(StackFrame frame) {
            astore(frame, 0);
        }

        @Override
        public String getCode() {
            return "0x4b";
        }
    },

    //0x4c
    ASTORE_1 {
        @Override
        public void execute(StackFrame frame) {
            astore(frame, 1);
        }

        @Override
        public String getCode() {
            return "0x4c";
        }
    },

    //0x4d
    ASTORE_2 {
        @Override
        public void execute(StackFrame frame) {
            astore(frame, 2);
        }

        @Override
        public String getCode() {
            return "0x4d";
        }
    },

    //0x4e
    ASTORE_3 {
        @Override
        public void execute(StackFrame frame) {
            astore(frame, 3);
        }

        @Override
        public String getCode() {
            return "0x4e";
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

        @Override
        public String getCode() {
            return "0x4f";
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

        @Override
        public String getCode() {
            return "0x50";
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

        @Override
        public String getCode() {
            return "0x51";
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

        @Override
        public String getCode() {
            return "0x52";
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

        @Override
        public String getCode() {
            return "0x53";
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

        @Override
        public String getCode() {
            return "0x54";
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

        @Override
        public String getCode() {
            return "0x55";
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

        @Override
        public String getCode() {
            return "0x56";
        }
    },

    //0x57
    POP {
        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.pop();
        }

        @Override
        public String getCode() {
            return "0x57";
        }
    },

    //0x58
    POP2 {
        @Override
        public void execute(StackFrame frame) {
            frame.operationStack.pop();
            frame.operationStack.pop();
        }

        @Override
        public String getCode() {
            return "0x58";
        }
    },

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
    },

    //0x5f
    SWAP {

        @Override
        public void execute(StackFrame frame) {
            Object pop1 = frame.operationStack.pop();
            Object pop2 = frame.operationStack.pop();
            frame.operationStack.push(pop1);
            frame.operationStack.push(pop2);
        }

        @Override
        public String getCode() {
            return "0x5f";
        }
    },

    //0x60
    IADD {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            int v2 = (int) frame.operationStack.pop();
            int result = v1 + v2;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x60";
        }
    },

    //0x61
    LADD {
        @Override
        public void execute(StackFrame frame) {
            long v1 = (long) frame.operationStack.pop();
            long v2 = (long) frame.operationStack.pop();
            long result = v1 + v2;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x61";
        }
    },

    //0x62
    FADD {
        @Override
        public void execute(StackFrame frame) {
            float v1 = (float) frame.operationStack.pop();
            float v2 = (float) frame.operationStack.pop();
            float result = v1 + v2;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x62";
        }
    },

    //0x63
    DADD {
        @Override
        public void execute(StackFrame frame) {
            double v1 = (double) frame.operationStack.pop();
            double v2 = (double) frame.operationStack.pop();
            double result = v1 + v2;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x63";
        }
    },

    //0x64
    ISUB {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            int v2 = (int) frame.operationStack.pop();
            int result = v2 - v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x64";
        }
    },

    //0x65
    LSUB {
        @Override
        public void execute(StackFrame frame) {
            long v1 = (long) frame.operationStack.pop();
            long v2 = (long) frame.operationStack.pop();
            long result = v2 - v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x65";
        }
    },

    //0x66
    FSUB {
        @Override
        public void execute(StackFrame frame) {
            float v1 = (float) frame.operationStack.pop();
            float v2 = (float) frame.operationStack.pop();
            float result = v2 - v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x66";
        }
    },

    //0x67
    DSUB {
        @Override
        public void execute(StackFrame frame) {
            double v1 = (double) frame.operationStack.pop();
            double v2 = (double) frame.operationStack.pop();
            double result = v2 - v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x67";
        }
    },

    //0x68
    IMUL {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            int v2 = (int) frame.operationStack.pop();
            int result = v1 * v2;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x68";
        }
    },

    //0x69
    LMUL {
        @Override
        public void execute(StackFrame frame) {
            long v1 = (long) frame.operationStack.pop();
            long v2 = (long) frame.operationStack.pop();
            long result = v1 * v2;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x69";
        }
    },

    //0x6a
    FMUL {
        @Override
        public void execute(StackFrame frame) {
            float v1 = (float) frame.operationStack.pop();
            float v2 = (float) frame.operationStack.pop();
            float result = v1 * v2;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x6a";
        }
    },

    //0x6b
    DMUL {
        @Override
        public void execute(StackFrame frame) {
            double v1 = (double) frame.operationStack.pop();
            double v2 = (double) frame.operationStack.pop();
            double result = v1 * v2;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x6b";
        }
    },

    //0x6c
    IDIV {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            int v2 = (int) frame.operationStack.pop();
            if (v1 == 0) {
                throw new RuntimeException("Div by 0");
            }
            int result = v2 / v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x6c";
        }
    },

    //0x6d
    LDIV {
        @Override
        public void execute(StackFrame frame) {
            long v1 = (long) frame.operationStack.pop();
            long v2 = (long) frame.operationStack.pop();
            if (v1 == 0L) {
                throw new RuntimeException("Div by 0L");
            }
            long result = v2 / v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x6d";
        }
    },

    //0x6e
    FDIV {
        @Override
        public void execute(StackFrame frame) {
            float v1 = (float) frame.operationStack.pop();
            float v2 = (float) frame.operationStack.pop();
            if (v1 == 0f) {
                throw new RuntimeException("Div by 0f");
            }
            float result = v2 / v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x6e";
        }
    },

    //0x6f
    DDIV {
        @Override
        public void execute(StackFrame frame) {
            double v1 = (double) frame.operationStack.pop();
            double v2 = (double) frame.operationStack.pop();
            if (v1 == 0.0) {
                throw new RuntimeException("Div by 0.0");
            }
            double result = v2 / v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x6f";
        }
    },

    //0x70
    IREM {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            int v2 = (int) frame.operationStack.pop();
            if (v1 == 0) {
                throw new RuntimeException("Rem by 0");
            }
            int result = v2 % v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x70";
        }
    },

    //0x71
    LREM {
        @Override
        public void execute(StackFrame frame) {
            long v1 = (long) frame.operationStack.pop();
            long v2 = (long) frame.operationStack.pop();
            if (v1 == 0L) {
                throw new RuntimeException("Rem by 0L");
            }
            long result = v2 % v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x71";
        }
    },

    //0x72
    FREM {
        @Override
        public void execute(StackFrame frame) {
            float v1 = (float) frame.operationStack.pop();
            float v2 = (float) frame.operationStack.pop();
            if (v1 == 0f) {
                throw new RuntimeException("Rem by 0f");
            }
            float result = v2 % v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x72";
        }
    },

    //0x73
    DREM {
        @Override
        public void execute(StackFrame frame) {
            double v1 = (double) frame.operationStack.pop();
            double v2 = (double) frame.operationStack.pop();
            if (v1 == 0.0) {
                throw new RuntimeException("Rem by 0.0");
            }
            double result = v2 % v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x73";
        }
    },

    //0x74
    INEG {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            frame.operationStack.push(-v1);
        }

        @Override
        public String getCode() {
            return "0x74";
        }
    },

    //0x75
    LNEG {
        @Override
        public void execute(StackFrame frame) {
            long v1 = (long) frame.operationStack.pop();
            frame.operationStack.push(-v1);
        }

        @Override
        public String getCode() {
            return "0x75";
        }
    },

    //0x76
    FNEG {
        @Override
        public void execute(StackFrame frame) {
            float v1 = (float) frame.operationStack.pop();
            frame.operationStack.push(-v1);
        }

        @Override
        public String getCode() {
            return "0x76";
        }
    },

    //0x77
    DNEG {
        @Override
        public void execute(StackFrame frame) {
            double v1 = (double) frame.operationStack.pop();
            frame.operationStack.push(-v1);
        }

        @Override
        public String getCode() {
            return "0x77";
        }
    },

    //0x78
    ISHL {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            int v2 = (int) frame.operationStack.pop();
            int s = v1 & 0x1f;
            int result = v2 << s;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x78";
        }
    },

    //0x79
    LSHL {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            long v2 = (long) frame.operationStack.pop();
            int s = v1 & 0x3f;
            long result = v2 << s;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x79";
        }
    },

    //0x7a
    ISHR {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            int v2 = (int) frame.operationStack.pop();
            int s = v1 & 0x1f;
            int result = v2 >> s;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x7a";
        }
    },

    //0x7b
    LSHR {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            long v2 = (long) frame.operationStack.pop();
            int s = v1 & 0x3f;
            long result = v2 >> s;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x7b";
        }
    },

    //0x7c
    IUSHR {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            int v2 = (int) frame.operationStack.pop();
            int s = v1 & 0x1f;
            int result = v2 >> s;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x7c";
        }
    },

    //0x7d
    LUSHR {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            long v2 = (long) frame.operationStack.pop();
            int s = v1 & 0x3f;
            long result = v2 >> s;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x7d";
        }
    },

    //0x7e
    IAND {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            int v2 = (int) frame.operationStack.pop();
            int result = v1 & v2;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x7e";
        }
    },

    //0x7f
    LAND {
        @Override
        public void execute(StackFrame frame) {
            long v1 = (long) frame.operationStack.pop();
            long v2 = (long) frame.operationStack.pop();
            long result = v1 & v2;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x7f";
        }
    },

    //0x80
    IOR {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            int v2 = (int) frame.operationStack.pop();
            int result = v1 | v2;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x80";
        }
    },

    //0x81
    LOR {
        @Override
        public void execute(StackFrame frame) {
            long v1 = (long) frame.operationStack.pop();
            long v2 = (long) frame.operationStack.pop();
            long result = v1 | v2;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x81";
        }
    },

    //0x82
    IXOR {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            int v2 = (int) frame.operationStack.pop();
            int result = v1 ^ v2;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x82";
        }
    },

    //0x83
    LXOR {
        @Override
        public void execute(StackFrame frame) {
            long v1 = (long) frame.operationStack.pop();
            long v2 = (long) frame.operationStack.pop();
            long result = v1 ^ v2;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x83";
        }
    },

    //0x84
    IINC {

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

        @Override
        public String getCode() {
            return "0x84";
        }
    },

    //0x85
    I2L {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            long result = (long)v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x85";
        }
    },

    //0x86
    I2F {
        @Override
        public void execute(StackFrame frame) {
            try {
                int v1 = (int) frame.operationStack.pop();
                float result = (float) v1;
                frame.operationStack.push(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public String getCode() {
            return "0x86";
        }
    },

    //0x87
    I2D {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            double result = (double) v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x87";
        }
    },

    //0x88
    L2I {
        @Override
        public void execute(StackFrame frame) {
            long v1 = (long) frame.operationStack.pop();
            int result = (int) v1;
            frame.operationStack.push(result );
        }

        @Override
        public String getCode() {
            return "0x88";
        }
    },

    //0x89
    L2F {
        @Override
        public void execute(StackFrame frame) {
            long v1 = (long) frame.operationStack.pop();
            float result = (float) v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x89";
        }
    },

    //0x8a
    L2D {
        @Override
        public void execute(StackFrame frame) {
            long v1 = (long) frame.operationStack.pop();
            double result = (double) v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x8a";
        }
    },

    //0x8b
    F2I {
        @Override
        public void execute(StackFrame frame) {
            float v1 = (float) frame.operationStack.pop();
            int result = (int) v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x8b";
        }
    },

    //0x8c
    F2L {
        @Override
        public void execute(StackFrame frame) {
            float v1 = (float) frame.operationStack.pop();
            long result = (long) v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x8c";
        }
    },

    //0x8d
    F2D {
        @Override
        public void execute(StackFrame frame) {
            float v1 = (float) frame.operationStack.pop();
            double result = (double) v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x8d";
        }
    },

    //0x8e
    D2I {
        @Override
        public void execute(StackFrame frame) {
            double v1 = (double) frame.operationStack.pop();
            int result = (int) v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x8e";
        }
    },

    //0x8f
    D2L {
        @Override
        public void execute(StackFrame frame) {
            double v1 = (double) frame.operationStack.pop();
            long result = (long) v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x8f";
        }
    },

    //0x90
    D2F {
        @Override
        public void execute(StackFrame frame) {
            double v1 = (double) frame.operationStack.pop();
            float result = (float) v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x90";
        }
    },

    //0x91
    I2B {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            byte result = (byte) v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x91";
        }
    },

    //0x92
    I2C {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            char result = (char) v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x92";
        }
    },

    //0x93
    I2S {
        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            short result = (short) v1;
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x93";
        }
    },

    //0x94
    LCMP {
        @Override
        public void execute(StackFrame frame) {
            long v1 = (long) frame.operationStack.pop();
            long v2 = (long) frame.operationStack.pop();
            int result = Long.compare(v1, v2);
            frame.operationStack.push(result);
        }

        @Override
        public String getCode() {
            return "0x94";
        }
    },

    //0x95
    FCMPL {
        @Override
        public void execute(StackFrame frame) {
            try {
                float v1 = (float) frame.operationStack.pop();
                float v2 = (float) frame.operationStack.pop();
                int result = Float.compare(v1, v2);
                frame.operationStack.push(result);
            } catch (Exception exception) {
                frame.operationStack.push(-1);
            }
        }

        @Override
        public String getCode() {
            return "0x95";
        }
    },

    //0x96
    FCMPG {
        @Override
        public void execute(StackFrame frame) {
            try {
                float v1 = (float) frame.operationStack.pop();
                float v2 = (float) frame.operationStack.pop();
                int result = Float.compare(v1, v2);
                frame.operationStack.push(result);
            } catch (Exception exception) {
                frame.operationStack.push(1);
            }
        }

        @Override
        public String getCode() {
            return "0x96";
        }
    },

    //0x97
    DCMPL {
        @Override
        public void execute(StackFrame frame) {
            try {
                float v1 = (float) frame.operationStack.pop();
                float v2 = (float) frame.operationStack.pop();
                int result = Float.compare(v1, v2);
                frame.operationStack.push(result);
            } catch (Exception exception) {
                frame.operationStack.push(-1);
            }
        }

        @Override
        public String getCode() {
            return "0x97";
        }
    },

    //0x98
    DCMPG {
        @Override
        public void execute(StackFrame frame) {
            try {
                float v1 = (float) frame.operationStack.pop();
                float v2 = (float) frame.operationStack.pop();
                int result = Float.compare(v1, v2);
                frame.operationStack.push(result);
            } catch (Exception exception) {
                frame.operationStack.push(1);
            }
        }

        @Override
        public String getCode() {
            return "0x98";
        }
    },

    //0x99
    IFEQ {

        public int offset;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            offset = reader.readShort();
        }

        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            if (v1 == 0) {
                go(frame, offset);
            }
        }

        @Override
        public String getCode() {
            return "0x99";
        }
    },

    //0x9a
    IFNE {

        public int offset;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            offset = reader.readShort();
        }

        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            if (v1 != 0) {
                go(frame, offset);
            }
        }

        @Override
        public String getCode() {
            return "0x9a";
        }
    },

    //0x9b
    IFLT {

        public int offset;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            offset = reader.readShort();
        }

        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            if (v1 < 0) {
                go(frame, offset);
            }
        }

        @Override
        public String getCode() {
            return "0x9b";
        }
    },

    //0x9c
    IFGE {

        public int offset;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            offset = reader.readShort();
        }

        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            if (v1 >= 0) {
                go(frame, offset);
            }
        }

        @Override
        public String getCode() {
            return "0x9c";
        }
    },

    //0x9d
    IFGT {

        public int offset;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            offset = reader.readShort();
        }

        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            if (v1 > 0) {
                go(frame, offset);
            }
        }

        @Override
        public String getCode() {
            return "0x9d";
        }
    },

    //0x9e
    IFLE {

        public int offset;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            offset = reader.readShort();
        }

        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            if (v1 <= 0) {
                go(frame, offset);
            }
        }

        @Override
        public String getCode() {
            return "0x9e";
        }
    },

    //0x9f
    IF_ICMPEQ {

        public int offset;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            offset = reader.readShort();
        }

        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            int v2 = (int) frame.operationStack.pop();
            if (v1 == v2) {
                go(frame, offset);
            }
        }

        @Override
        public String getCode() {
            return "0x9f";
        }
    },

    //0xa0
    IF_ICMPNE {

        public int offset;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            offset = reader.readShort();
        }

        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            int v2 = (int) frame.operationStack.pop();
            if (v1 != v2) {
                go(frame, offset);
            }
        }

        @Override
        public String getCode() {
            return "0xa0";
        }
    },

    //0xa1
    IF_ICMPLT {

        public int offset;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            offset = reader.readShort();
        }

        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            int v2 = (int) frame.operationStack.pop();
            if (v1 > v2) {
                go(frame, offset);
            }
        }

        @Override
        public String getCode() {
            return "0xa1";
        }
    },

    //0xa2
    IF_ICMPGE {

        public int offset;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            offset = reader.readShort();
        }

        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            int v2 = (int) frame.operationStack.pop();
            if (v1 <= v2) {
                go(frame, offset);
            }
        }

        @Override
        public String getCode() {
            return "0xa2";
        }
    },

    //0xa3
    IF_ICMPGT {

        public int offset;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            offset = reader.readShort();
        }

        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            int v2 = (int) frame.operationStack.pop();
            if (v1 < v2) {
                go(frame, offset);
            }
        }

        @Override
        public String getCode() {
            return "0xa3";
        }
    },

    //0xa4
    IF_ICMPLE {

        public int offset;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            offset = reader.readShort();
        }

        @Override
        public void execute(StackFrame frame) {
            int v1 = (int) frame.operationStack.pop();
            int v2 = (int) frame.operationStack.pop();
            if (v1 >= v2) {
                go(frame, offset);
            }
        }

        @Override
        public String getCode() {
            return "0xa4";
        }
    },

    //0xa5
    IF_ACMPEQ {

        public int offset;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            offset = reader.readShort();
        }

        @Override
        public void execute(StackFrame frame) {
            Object v1 = frame.operationStack.pop();
            Object v2 = frame.operationStack.pop();
            if (v1 == v2) {
                go(frame, offset);
            }
        }

        @Override
        public String getCode() {
            return "0xa5";
        }
    },

    //0xa6
    IF_ACMPNE {

        public int offset;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            offset = reader.readShort();
        }

        @Override
        public void execute(StackFrame frame) {
            Object v1 = frame.operationStack.pop();
            Object v2 = frame.operationStack.pop();
            if (v1 != v2) {
                go(frame, offset);
            }
        }

        @Override
        public String getCode() {
            return "0xa6";
        }
    },

    //0xa7
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
    },

    //0xaa
    TABLE_SWITCH {

        int defaultOffset;
        int low;
        int high;
        int[] offsets;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            reader.skipPadding();
            defaultOffset = reader.readInt();
            low = reader.readInt();
            high = reader.readInt();
            int switchCount = high - low + 1;
            offsets = reader.readInts(switchCount);
        }

        @Override
        public void execute(StackFrame frame) {
            int index = (int) frame.operationStack.pop();
            int gotoOffset;
            if (index >= low && index <= high) {
                gotoOffset = index - low;
            } else {
                gotoOffset = defaultOffset;
            }
            go(frame, gotoOffset);
        }

        @Override
        public String getCode() {
            return "0xaa";
        }
    },

    //0xab
    LOOK_UP_SWITCH {

        int defaultOffset;
        int npairs;
        int[] matchCount;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            reader.skipPadding();
            defaultOffset = reader.readInt();
            npairs = reader.readInt();
            matchCount = reader.readInts(npairs * 2);
        }

        @Override
        public void execute(StackFrame frame) {
            int index = (int) frame.operationStack.pop();
            int offset = defaultOffset;
            for (int i = 0; i < npairs * 2; i += 2) {
                if (matchCount[i] == index) {
                    offset = matchCount[i + 1];
                    break;
                }
            }
            go(frame, offset);
        }

        @Override
        public String getCode() {
            return "0xab";
        }
    },

    //0xac
    IRETURN {
        @Override
        public void execute(StackFrame frame) {
            StackFrame currentFrame = frame.jvmThread.popFrame();
            StackFrame callerFrame = frame.jvmThread.popFrame();
            int v1 = (int) currentFrame.operationStack.pop();
            callerFrame.operationStack.push(v1);
        }

        @Override
        public String getCode() {
            return "0xac";
        }
    },

    //0xad
    LRETURN {
        @Override
        public void execute(StackFrame frame) {
            StackFrame currentFrame = frame.jvmThread.popFrame();
            StackFrame callerFrame = frame.jvmThread.popFrame();
            long v1 = (long) currentFrame.operationStack.pop();
            callerFrame.operationStack.push(v1);
        }

        @Override
        public String getCode() {
            return "0xad";
        }
    },

    //0xae
    FRETURN {
        @Override
        public void execute(StackFrame frame) {
            StackFrame currentFrame = frame.jvmThread.popFrame();
            StackFrame callerFrame = frame.jvmThread.popFrame();
            float v1 = (long) currentFrame.operationStack.pop();
            callerFrame.operationStack.push(v1);
        }

        @Override
        public String getCode() {
            return "0xae";
        }
    },

    //0xaf
    DRETURN {
        @Override
        public void execute(StackFrame frame) {
            StackFrame currentFrame = frame.jvmThread.popFrame();
            StackFrame callerFrame = frame.jvmThread.popFrame();
            double v1 = (double) currentFrame.operationStack.pop();
            callerFrame.operationStack.push(v1);
        }

        @Override
        public String getCode() {
            return "0xaf";
        }
    },

    //0xb0
    ARETURN {
        @Override
        public void execute(StackFrame frame) {
            StackFrame currentFrame = frame.jvmThread.popFrame();
            StackFrame callerFrame = frame.jvmThread.popFrame();
            Object v1 = currentFrame.operationStack.pop();
            callerFrame.operationStack.push(v1);
        }

        @Override
        public String getCode() {
            return "0xb0";
        }
    },

    //0xb1
    RETURN {
        @Override
        public void execute(StackFrame frame) {
            frame.jvmThread.popFrame();
        }

        @Override
        public String getCode() {
            return "0xb1";
        }
    },

    //0xb2
    GET_STATIC {

        short index;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            index = reader.readShort();
        }

        @Override
        public void execute(StackFrame frame) {
            if (!frame.jvmMethod.jvmClass.isClinitCalled()) {
                frame.jvmMethod.jvmClass.callClinit();
            }
            JvmClass<?> jvmClass = frame.jvmMethod.jvmClass;
            RuntimeConstantPool constantPool = JavaVirtualMachine.getMethodArea().getConstantPool(jvmClass);
            FieldReference fieldReference = (FieldReference) constantPool.get(index);
            JvmField jvmField = fieldReference.resolveField();
            System.out.println("GETStatiC "+jvmField.jvmClass.getStaticFieldVal(jvmField.getFieldIndex()));
            frame.operationStack.push(jvmField.jvmClass.getStaticFieldVal(jvmField.getFieldIndex()));

        }

        @Override
        public String getCode() {
            return "0xb2";
        }
    },

    //0xb3
    PUT_STATIC {

        short index;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            index = reader.readShort();
        }

        @Override
        public void execute(StackFrame frame) {
            if (!frame.jvmMethod.jvmClass.isClinitCalled()) {
                frame.jvmMethod.jvmClass.callClinit();
            }
            JvmClass<?> jvmClass = frame.jvmMethod.jvmClass;
            RuntimeConstantPool constantPool = JavaVirtualMachine.getMethodArea().getConstantPool(jvmClass);
            FieldReference fieldReference = (FieldReference) constantPool.get(index);
            JvmField jvmField = fieldReference.resolveField();
            if (!jvmField.isStatic()) {
                throw new RuntimeException("can't assign un static field in PUT_STATIC operation");
            }
            if (jvmField.isFinal()) {
                if (!frame.jvmMethod.getMethodName().equals("<clinit>")) {
                    throw new RuntimeException("can't assign un static field in non clinit method");
                }
            }
            jvmClass.setStaticFieldVal(jvmField.getFieldIndex(), frame.operationStack.pop());
        }

        @Override
        public String getCode() {
            return "0xb3";
        }
    },

    //0xb4
    GET_FIELD {

        short index;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            index = reader.readShort();
        }

        @Override
        public void execute(StackFrame frame) {
            JvmClass<?> jvmClass = frame.jvmMethod.jvmClass;
            RuntimeConstantPool constantPool = JavaVirtualMachine.getMethodArea().getConstantPool(jvmClass);
            FieldReference fieldReference = (FieldReference) constantPool.get(index);
            JvmField jvmField = fieldReference.resolveField();
            JvmObject obj = (JvmObject) frame.operationStack.pop();
            frame.operationStack.push(obj.getInstanceFieldVal(jvmField.getFieldIndex()));
        }

        @Override
        public String getCode() {
            return "0xb4";
        }
    },

    //0xb5
    PUT_FIELD {

        short index;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            index = reader.readShort();
        }

        @Override
        public void execute(StackFrame frame) {
            JvmClass<?> jvmClass = frame.jvmMethod.jvmClass;
            RuntimeConstantPool constantPool = JavaVirtualMachine.getMethodArea().getConstantPool(jvmClass);
            FieldReference fieldReference = (FieldReference) constantPool.get(index);
            JvmField jvmField = fieldReference.resolveField();
            Object v1 = frame.operationStack.pop();
            JvmObject obj = (JvmObject) frame.operationStack.pop();
            obj.setInstanceFieldVal(jvmField.getFieldIndex(), v1);
        }

        @Override
        public String getCode() {
            return "0xb5";
        }
    },

    //0xb6
    INVOKE_VIRTUAL {

        short offset;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            offset = reader.readShort();
        }

        @Override
        public void execute(StackFrame frame) {
            RuntimeConstantPool constantPool =
                    JavaVirtualMachine.getMethodArea().getConstantPool(frame.jvmMethod.jvmClass);
            MethodRef methodRef = (MethodRef) constantPool.get(offset);
            methodRef.resolveMethod();
            invokeMethod(frame, methodRef.jvmMethod);
        }

        @Override
        public String getCode() {
            return "0xb6";
        }
    },

    //0xb7
    INVOKE_SPECIAL {

        short offset;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            offset = reader.readShort();
        }

        @Override
        public void execute(StackFrame frame) {
            RuntimeConstantPool constantPool = frame.jvmMethod.jvmClass.constantPool;
            MethodRef methodRef = (MethodRef) constantPool.get(offset);
            methodRef.resolveMethod();
            final JvmMethod method = methodRef.jvmMethod;
            final JvmClass<?> jvmClass = method.jvmClass;

        }

        @Override
        public String getCode() {
            return "0xb7";
        }
    },

    //0xb8
    INVOKE_STATIC {

        short offset;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            offset = reader.readShort();
        }

        @Override
        public void execute(StackFrame frame) {
            if (!frame.jvmMethod.jvmClass.isClinitCalled()) {
                frame.jvmMethod.jvmClass.callClinit();
            }
            RuntimeConstantPool constantPool =
                    JavaVirtualMachine.getMethodArea().getConstantPool(frame.jvmMethod.jvmClass);
            MethodRef methodRef = (MethodRef) constantPool.get(offset);
            methodRef.resolveMethod();
            invokeMethod(frame, methodRef.jvmMethod);
        }

        @Override
        public String getCode() {
            return "0xb8";
        }
    },

    //0xb9
    INVOKE_INTERFACE {
        @Override
        public void execute(StackFrame frame) {

        }

        @Override
        public String getCode() {
            return "0xb9";
        }
    },

    //0xbb
    NEW {

        short offset;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            offset = reader.readShort();
        }

        @Override
        public void execute(StackFrame frame) {
            if (!frame.jvmMethod.jvmClass.isClinitCalled()) {
                frame.jvmMethod.jvmClass.callClinit();
            }
            ClassReference classReference = (ClassReference) JavaVirtualMachine
                    .getMethodArea().getConstantPool(frame.jvmMethod.jvmClass).get(offset);
            classReference.loadClass();
            if (classReference.jvmClass.isInterface() || classReference.jvmClass.isAbstract()) {
                throw new RuntimeException("Can't create instance of " + classReference.jvmClass);
            }
            JvmObject newInstance = classReference.jvmClass.newInstance();
            frame.operationStack.push(newInstance);
        }

        @Override
        public String getCode() {
            return "0xbb";
        }
    },

    //0xc0
    CHECK_CAST {
        short offset;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            offset = reader.readShort();
        }

        @Override
        public void execute(StackFrame frame) {
            RuntimeConstantPool runtimeConstantPool =
                    JavaVirtualMachine.getMethodArea().getConstantPool(frame.jvmMethod.jvmClass);
            JvmObject v1 = (JvmObject) frame.operationStack.pop();
            if (v1 == null) {
                frame.operationStack.push(0);
                return;
            }
            ClassReference reference = (ClassReference) runtimeConstantPool.get(offset);
            JvmClass<?> jvmClass = reference.loadClass();
            if (!v1.jvmClass.isAssignFrom(jvmClass)) {
                throw new RuntimeException("java.lang.ClassCastException");
            }
        }

        @Override
        public String getCode() {
            return "0xc0";
        }
    },

    //0xc1
    INSTANCE_OF {

        short offset;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            offset = reader.readShort();
        }

        @Override
        public void execute(StackFrame frame) {
            RuntimeConstantPool runtimeConstantPool =
                    JavaVirtualMachine.getMethodArea().getConstantPool(frame.jvmMethod.jvmClass);
            JvmObject v1 = (JvmObject) frame.operationStack.pop();
            if (v1 == null) {
                frame.operationStack.push(0);
                return;
            }
            ClassReference reference = (ClassReference) runtimeConstantPool.get(offset);
            JvmClass<?> jvmClass = reference.loadClass();
            if (v1.jvmClass.isAssignFrom(jvmClass)) {
                frame.operationStack.push(1);
            } else {
                frame.operationStack.push(0);
            }
        }

        @Override
        public String getCode() {
            return "0xc1";
        }
    },

    WIDE {
        @Override
        public void execute(StackFrame frame) {

        }

        @Override
        public String getCode() {
            return null;
        }
    },

    //0xc6
    IF_NULL {

        short offset;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            offset = reader.readShort();
        }

        @Override
        public void execute(StackFrame frame) {
            Object v1 = frame.operationStack.pop();
            if (v1 == null) {
                go(frame, offset);
            }
        }

        @Override
        public String getCode() {
            return "0xc6";
        }
    },

    //0xc7
    IF_NONNULL {

        short offset;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            offset = reader.readShort();
        }

        @Override
        public void execute(StackFrame frame) {
            Object v1 = frame.operationStack.pop();
            if (v1 != null) {
                go(frame, offset);
            }
        }

        @Override
        public String getCode() {
            return "0xc7";
        }
    },

    //0xc8
    GOTO_W {

        int offset;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            offset = reader.readInt();
        }

        @Override
        public void execute(StackFrame frame) {
            go(frame, offset);
        }

        @Override
        public String getCode() {
            return "0xc8";
        }
    }
    ;

    public void go(StackFrame frame, int offset) {
        int pc = frame.getJvmThread().getPc();
        pc = pc + offset;
        frame.setNextPC(pc);
    }

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

    public void iload(StackFrame frame, int index) {
        int val = (int) frame.localVariable.get(index);
        frame.operationStack.push(val);
    }

    public void lload(StackFrame frame, int index) {
        long val = (long) frame.localVariable.get(index);
        frame.operationStack.push(val);
    }

    public void fload(StackFrame frame, int index) {
        float val = (float) frame.localVariable.get(index);
        frame.operationStack.push(val);
    }

    public void dload(StackFrame frame, int index) {
        double val = (double) frame.localVariable.get(index);
        frame.operationStack.push(val);
    }

    public void aload(StackFrame frame, int index) {
        Object val = frame.localVariable.get(index);
        frame.operationStack.push(val);
    }

    public void ldc(StackFrame stackFrame, int index) {
        RuntimeConstantPool constantPool = JavaVirtualMachine.getMethodArea()
                .getConstantPool(stackFrame.jvmMethod.jvmClass);
        Object constant = constantPool.get(index);
        stackFrame.operationStack.push(constant);
    }

    public void invokeMethod(StackFrame currentFrame, JvmMethod invokeMethod) {
        StackFrame invokeFrame = new StackFrame(invokeMethod);
        currentFrame.jvmThread.pushFrame(invokeFrame);
        int argsCount = invokeMethod.getArgsCount();
        for (int i = argsCount - 1;i >= 0; i--) {
            Object pop = currentFrame.operationStack.pop();
            invokeFrame.localVariable.set(i, pop);
        }
    }

}
