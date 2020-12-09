package com.keguoyu.minijvm.lang;

import com.keguoyu.minijvm.operation.OperationFactory;
import com.sun.tools.classfile.Method;

public class JvmMethod {
    public final Method method;
    public final JvmClass<?> jvmClass;

    public JvmMethod(Method method, JvmClass<?> jvmClass) {
        this.method = method;
        this.jvmClass = jvmClass;
    }

    /**
     * 有可能直接调用反射
     */
    public void invoke() {
        OperationFactory.checkInitOrNot();
        BytecodeInvoker.invoke(this);
    }

}
