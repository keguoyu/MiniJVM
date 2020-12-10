package com.keguoyu.minijvm.operation;

import com.keguoyu.minijvm.lang.BytecodeReader;
import com.keguoyu.minijvm.main.JavaVirtualMachine;
import com.keguoyu.minijvm.data.ref.ClassReference;
import com.keguoyu.minijvm.runtime.data.StackFrame;

public enum NewOperations implements Operation {

    //0xbb
    NEW {

        short offset;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            offset = reader.readShort();
        }

        @Override
        public void execute(StackFrame frame) {
            ClassReference classReference = (ClassReference) JavaVirtualMachine
                    .getMethodArea().getConstantPool(frame.jvmMethod.jvmClass).get(offset);
            classReference.loadClass();
            if (classReference.jvmClass.isInterface() || classReference.jvmClass.isAbstract()) {
                throw new RuntimeException("Can't create instance of " + classReference.jvmClass);
            }
            Object newInstance = classReference.jvmClass.newInstance();
            frame.operationStack.push(newInstance);
        }

        @Override
        public String getCode() {
            return "0xbb";
        }
    }
    ;
}
