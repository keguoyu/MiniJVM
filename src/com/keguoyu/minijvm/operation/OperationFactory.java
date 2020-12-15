package com.keguoyu.minijvm.operation;

import java.util.HashMap;
import java.util.Map;

public class OperationFactory {

    private static final Map<String, Operation> map = new HashMap<>();

    private static boolean init = false;

    /**
     * 初始化指令集
     */
    public static void checkInitOrNot() {
        synchronized (OperationFactory.class) {
            if (!init) {
                Operation[] groups = OperationsGroup.values();
                for (Operation group: groups) {
                    map.put(group.getCode(), group);
                }
                init = true;
            }
        }
    }

    public static Operation valueOf(String code) {
        return map.get(code);
    }
}
