package com.keguoyu.minijvm.lang;

import com.keguoyu.minijvm.operation.Operation;
import com.keguoyu.minijvm.operation.Operations;
import com.keguoyu.minijvm.runtime.data.JvmThread;
import com.keguoyu.minijvm.runtime.data.StackFrame;
import com.sun.tools.classfile.Code_attribute;
import com.sun.tools.classfile.Method;

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

    private void loop(JvmThread thread, byte[] code) {
        StackFrame frame = thread.popFrame();
        BytecodeReader reader = new BytecodeReader();
        int pc;
        byte opcode;
        Operation operation;
        for(;;) {

        }
    }

}
