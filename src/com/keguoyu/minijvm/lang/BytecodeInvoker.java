package com.keguoyu.minijvm.lang;

import com.keguoyu.minijvm.operation.Operation;
import com.keguoyu.minijvm.operation.OperationFactory;
import com.keguoyu.minijvm.runtime.data.JvmThread;
import com.keguoyu.minijvm.runtime.data.StackFrame;

import java.util.Arrays;

public class BytecodeInvoker {

    public static void invoke(JvmMethod jvmMethod) {
        StackFrame stackFrame = new StackFrame(jvmMethod);
        JvmThread thread = new JvmThread();
        thread.pushFrame(stackFrame);
        loop(thread, jvmMethod.getCode());
    }

    public static String bytesToHexFun2(byte b) {
        return String.format("0x%02x", b & 0xff);
    }

    private static void loop(JvmThread thread, byte[] code) {
        for (byte b: code) {
            System.out.println(bytesToHexFun2(b));
        }
        StackFrame frame = thread.current();
        BytecodeReader reader = new BytecodeReader();
        byte opcode;
        Operation operation;
        System.out.println(Arrays.toString(code));
        do {
            int nextPC = frame.getNextPC();
            thread.setPc(nextPC);
            reader.reset(code, nextPC);
            opcode = reader.readByte();

            operation = OperationFactory.valueOf(bytesToHexFun2(opcode));
            System.out.println(nextPC + "====>" + opcode +"====>" + bytesToHexFun2(opcode) + "====>" + operation + "===>" + frame);
            operation.fetchOperands(reader);
            frame.setNextPC(reader.pc);
            operation.execute(frame);

            System.out.println();
        } while (thread.current() != null);
    }

}
