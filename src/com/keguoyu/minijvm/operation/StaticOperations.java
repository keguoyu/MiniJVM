package com.keguoyu.minijvm.operation;

import com.keguoyu.minijvm.lang.BytecodeReader;
import com.keguoyu.minijvm.lang.JvmClass;
import com.keguoyu.minijvm.lang.JvmField;
import com.keguoyu.minijvm.main.JavaVirtualMachine;
import com.keguoyu.minijvm.data.ref.FieldReference;
import com.keguoyu.minijvm.runtime.SingleConstantPool;
import com.keguoyu.minijvm.runtime.data.StackFrame;

public enum StaticOperations implements Operation {

    //0xb3
    PUT_STATIC {

        short index;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            index = reader.readShort();
        }

        @Override
        public void execute(StackFrame frame) {
            JvmClass<?> jvmClass = frame.jvmMethod.jvmClass;
            SingleConstantPool constantPool = JavaVirtualMachine.getMethodArea().getConstantPool(jvmClass);
            FieldReference fieldReference = (FieldReference) constantPool.get(index);
            JvmField jvmField = fieldReference.resolveField();
            jvmClass.setStaticFieldVal(jvmField.getFieldIndex(), frame.operationStack.pop());
        }

        @Override
        public String getCode() {
            return "0xb3";
        }
    },

    GET_STATIC {

        short index;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            index = reader.readShort();
        }

        @Override
        public void execute(StackFrame frame) {
            JvmClass<?> jvmClass = frame.jvmMethod.jvmClass;
            SingleConstantPool constantPool = JavaVirtualMachine.getMethodArea().getConstantPool(jvmClass);
            FieldReference fieldReference = (FieldReference) constantPool.get(index);
            JvmField jvmField = fieldReference.resolveField();
            frame.operationStack.push(jvmClass.getStaticFieldVal(jvmField.getFieldIndex()));
        }

        @Override
        public String getCode() {
            return "0xb2";
        }
    }


}
