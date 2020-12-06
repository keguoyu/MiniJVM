package com.keguoyu.minijvm.lang;

public class BytecodeReader {

    public byte[] code;
    public int pc;

    public byte read1Byte() {
        byte b = code[pc];
        pc++;
        return b;
    }

    public int read2Byte() {
        byte b1= read1Byte();
        byte b2 = read1Byte();
        return b1 << 8 | b2;
    }

    public int read4Byte() {
        byte b1 = read1Byte();
        byte b2 = read1Byte();
        byte b3 = read1Byte();
        byte b4 = read1Byte();
        return b1 << 24 | b2 << 16 | b3 << 8 | b4;
    }

    public void reset() {
        code = null;
        pc = 0;
    }

}
