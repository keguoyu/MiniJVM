package com.keguoyu.minijvm.lang;

import com.keguoyu.minijvm.operation.OperationFactory;
import com.sun.tools.classfile.*;

public class JvmMethod {
    public final Method method;
    public final JvmClass<?> jvmClass;
    public final int maxLocalsVarsSize;
    public final int maxOperandsSize;
    private final Code_attribute codeAttribute;

    private String parameterTypes = "";
    private String returnType = "";

    private String methodName = "";

    //方法的参数个数
    private int argsCount = 0;

    public JvmMethod(Method method, JvmClass<?> jvmClass) {
        this.method = method;
        this.jvmClass = jvmClass;
        codeAttribute = (Code_attribute) method.attributes.get("Code");
        maxLocalsVarsSize = codeAttribute == null ? 0 : codeAttribute.max_locals;
        maxOperandsSize = codeAttribute == null ? 0 : codeAttribute.max_stack;
        parseMethodInfo();
    }

    private void parseMethodInfo() {
        Descriptor descriptor = method.descriptor;
        try {
            argsCount = descriptor.getParameterCount(jvmClass.classFile.constant_pool);
            //如果不是静态方法 方法参数需要加1 因为第一个参数默认就是this
            if (!method.access_flags.is(AccessFlags.ACC_STATIC)) {
                argsCount++;
            }
            parameterTypes = descriptor.getParameterTypes(jvmClass.classFile.constant_pool);
            returnType = descriptor.getReturnType(jvmClass.classFile.constant_pool);
            methodName = method.getName(jvmClass.classFile.constant_pool);
        } catch (ConstantPoolException | Descriptor.InvalidDescriptor e) {
            e.printStackTrace();
        }
    }

    public int getArgsCount() {
        return argsCount;
    }

    public String getParameterTypes() {
        return parameterTypes;
    }

    public String getReturnType() {
        return returnType;
    }

    public byte[] getCode() {
        return codeAttribute.code;
    }

    public void invoke() {
        OperationFactory.checkInitOrNot();
        BytecodeInvoker.invoke(this);
    }

    public String getMethodName() {
        return methodName;
    }

    public String getName() {
        String name = "";
        try {
            name = method.getName(jvmClass.classFile.constant_pool);
        } catch (ConstantPoolException e) {
            e.printStackTrace();
        }
        return name;
    }

    public String getType() {
        String type = "";
        try {
            type = method.descriptor.getFieldType(jvmClass.classFile.constant_pool);
        } catch (ConstantPoolException | Descriptor.InvalidDescriptor e) {
            e.printStackTrace();
        }
        return type;
    }

}
