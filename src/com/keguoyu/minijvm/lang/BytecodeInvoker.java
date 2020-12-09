package com.keguoyu.minijvm.lang;

import com.keguoyu.minijvm.operation.Operation;
import com.keguoyu.minijvm.operation.OperationFactory;
import com.keguoyu.minijvm.runtime.data.JvmThread;
import com.keguoyu.minijvm.runtime.data.StackFrame;
import com.sun.tools.classfile.Code_attribute;
import com.sun.tools.classfile.Method;

import java.util.Arrays;

public class BytecodeInvoker {

    public void invoke(JvmMethod jvmMethod) {
        final Method method = jvmMethod.method;
        Code_attribute codeAttribute = (Code_attribute) method.attributes.get("Code");
        byte[] code = codeAttribute.code;
        StackFrame stackFrame = new StackFrame(codeAttribute.max_locals, codeAttribute.max_stack);
        JvmThread thread = new JvmThread();
        thread.pushFrame(stackFrame);
        loop(thread, code);
    }

    public static String bytesToHexFun2(byte b) {
        return String.format("0x%02x", b & 0xff);
    }

    private void loop(JvmThread thread, byte[] code) {
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
            System.out.println(nextPC + "====>" + operation + "===>" + frame);
            operation.fetchOperands(reader);
            frame.setNextPC(reader.pc);
            operation.execute(frame);

            System.out.println();
        } while (thread.current() != null);
    }

}
