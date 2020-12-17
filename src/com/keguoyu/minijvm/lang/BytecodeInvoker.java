package com.keguoyu.minijvm.lang;

import com.keguoyu.minijvm.operation.Operation;
import com.keguoyu.minijvm.operation.OperationFactory;
import com.keguoyu.minijvm.runtime.data.JvmThread;
import com.keguoyu.minijvm.runtime.data.StackFrame;

public class BytecodeInvoker {

    /**
     * 执行Class的clinit方法
     *
     * 执行new指令创建类实例，但类还没有被初始化。
     * ·执行putstatic、getstatic指令存取类的静态变量，但声明该字段
     * 的类还没有被初始化。
     * ·执行invokestatic调用类的静态方法，但声明该方法的类还没
     * 有被初始化。
     * ·当初始化一个类时，如果类的超类还没有被初始化，要先初始化类的超类。
     * ·执行某些反射操作时。
     *
     */
    public static void initClass(JvmThread jvmThread, JvmClass<?> jvmClass) {
        jvmClass.setClinitCalled(true);
        scheduleInit(jvmThread, jvmClass);
        initSuperClass(jvmThread, jvmClass);
    }

    private static void scheduleInit(JvmThread jvmThread, JvmClass<?> jvmClass) {
        JvmMethod clinitMethod = jvmClass.getMethod("<clinit>", "()V");
        if (clinitMethod != null && clinitMethod.jvmClass == jvmClass) {
            StackFrame stackFrame = new StackFrame(clinitMethod);
            jvmThread.pushFrame(stackFrame);
        }
    }

    private static void initSuperClass(JvmThread jvmThread, JvmClass<?> jvmClass) {
        if (!jvmClass.isInterface()) {
            if (jvmClass.superClass != null && !jvmClass.isClinitCalled()) {
                initClass(jvmThread, jvmClass.superClass);
            }
        }
    }

    public static void invoke(JvmMethod jvmMethod) {
        StackFrame stackFrame = new StackFrame(jvmMethod);
        JvmThread thread = new JvmThread();
        thread.pushFrame(stackFrame);
        loop(thread);
    }


    public static String bytesToHexFun2(byte b) {
        return String.format("0x%02x", b & 0xff);
    }

    private static void loop(JvmThread thread) {

        BytecodeReader reader = new BytecodeReader();
        byte opcode;
        Operation operation;

        do {
            StackFrame frame = thread.current();
            byte[] code = frame.jvmMethod.getCode();
            int nextPC = frame.getNextPC();
            thread.setPc(nextPC);
            reader.reset(code, nextPC);
            opcode = reader.readByte();

            operation = OperationFactory.valueOf(bytesToHexFun2(opcode));
            operation.fetchOperands(reader);
            frame.setNextPC(reader.pc);
            operation.execute(frame);
        } while (thread.current() != null);
    }

}
