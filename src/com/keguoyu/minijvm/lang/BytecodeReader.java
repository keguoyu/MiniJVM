package com.keguoyu.minijvm.lang;

import java.nio.ByteBuffer;

public class BytecodeReader {

    public byte[] code;
    public int pc = 0;

    public byte readByte() {
        byte b = code[pc];
        pc++;
        return b;
    }

    public short readShort() {
        byte b1= readByte();
        byte b2 =  readByte();
        ByteBuffer byteBuffer = ByteBuffer.allocate(2);
        byteBuffer.put(new byte[]{b1, b2});
        return byteBuffer.getShort(0);
    }

    public int readInt() {
        byte b1 = readByte();
        byte b2 = readByte();
        byte b3 = readByte();
        byte b4 = readByte();
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        byteBuffer.put(new byte[]{b1, b2, b3, b4});
        return byteBuffer.getInt(0);
    }

    public int[] readInts(int number) {
        int[] ints = new int[number];
        for (int i = 0; i< ints.length; i++) {
            ints[i] = readInt();
        }
        return ints;
    }

    public void skipPadding() {
        while (pc % 4 != 0) {
            readByte();
        }
    }

    public void reset(byte[] code, int pc) {
        this.code = code;
        this.pc = pc;
    }

}
