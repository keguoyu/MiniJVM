package com.keguoyu.minijvm.operation;

import com.keguoyu.minijvm.utils.Debugger;

import java.util.*;

public class OperationFactory {

    private static volatile boolean init = false;

    private static final Map<String, Operation> map = new HashMap<>(256);

    private static void initOperation() {
        List<Operation> operations = new ArrayList<>();
        operations.addAll(Arrays.asList(AndOperations.values()));
        operations.addAll(Arrays.asList(CastOperations.values()));
        operations.addAll(Arrays.asList(CmpOperations.values()));
        operations.addAll(Arrays.asList(CondOperations.values()));
        operations.addAll(Arrays.asList(ConstOperations.values()));
        operations.addAll(Arrays.asList(DupOperations.values()));
        operations.addAll(Arrays.asList(IncOperations.values()));
        operations.addAll(Arrays.asList(LoadOperations.values()));
        operations.addAll(Arrays.asList(MathOperations.values()));
        operations.addAll(Arrays.asList(OrOperations.values()));
        operations.addAll(Arrays.asList(PopOperations.values()));
        operations.addAll(Arrays.asList(PushOperations.values()));
        operations.addAll(Arrays.asList(ShlOperations.values()));
        operations.addAll(Arrays.asList(StoreOperations.values()));
        operations.addAll(Arrays.asList(SwapOperations.values()));
        operations.addAll(Arrays.asList(GotoOperations.values()));
        operations.addAll(Arrays.asList(ReturnOperations.values()));
        operations.addAll(Arrays.asList(LdcOperations.values()));
        operations.addAll(Arrays.asList(NewOperations.values()));
        operations.addAll(Arrays.asList(StaticOperations.values()));
        operations.addAll(Arrays.asList(FieldOperations.values()));
        for (Operation operation : operations) {
            map.put(operation.getCode(), operation);
        }
        Debugger.printf("Load %1s operations successfully \n", map.size());
    }

    public static void checkInitOrNot() {
        synchronized (OperationFactory.class) {
            if (!init) {
                initOperation();
                init = true;
            }
        }
    }

    public static Operation valueOf(String code) {
        return map.get(code);
    }

}
