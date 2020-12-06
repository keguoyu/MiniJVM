package com.keguoyu.minijvm.lang;

public class BytecodeReader {

    public byte[] code;
    public int pc;

    public byte readByte() {
        byte b = code[pc];
        pc++;
        return b;
    }

    public short readShort() {
        byte b1= readByte();
        byte b2 = readByte();
        return (short) (b1 << 8 | b2);
    }

    public int readInt() {
        byte b1 = readByte();
        byte b2 = readByte();
        byte b3 = readByte();
        byte b4 = readByte();
        return b1 << 24 | b2 << 16 | b3 << 8 | b4;
    }

    public void reset() {
        code = null;
        pc = 0;
    }

}
