package com.keguoyu.minijvm.lang;

import com.keguoyu.minijvm.operation.Operation;
import com.keguoyu.minijvm.operation.OperationFactory;
import com.keguoyu.minijvm.runtime.data.JvmThread;
import com.keguoyu.minijvm.runtime.data.StackFrame;
import com.sun.tools.classfile.Code_attribute;
import com.sun.tools.classfile.Method;

public class BytecodeInvoker {

    public void invoke(JvmMethod jvmMethod) {
        final Method method = jvmMethod.method;
        Code_attribute codeAttribute = (Code_attribute) method.attributes.get("Code");
        byte[] code = codeAttribute.code;
        for (byte b : code) {
            System.out.println(OperationFactory.valueOf(b));
        }
        StackFrame stackFrame = new StackFrame(codeAttribute.max_locals, codeAttribute.max_stack);
        JvmThread thread = new JvmThread();
        thread.pushFrame(stackFrame);
        loop(thread, code);
    }

    private void loop(JvmThread thread, byte[] code) {
        StackFrame frame = thread.current();
        BytecodeReader reader = new BytecodeReader();
        int pc;
        byte opcode;
        Operation operation;
        for(;;) {

        }
    }

}
