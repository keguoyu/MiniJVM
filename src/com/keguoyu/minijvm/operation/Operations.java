package com.keguoyu.minijvm.operation;

import java.util.HashMap;
import java.util.Map;

public class Operations {

    private static final Map<Integer, Operation> map = new HashMap<>();

    static {
        map.put(0x00, ConstOperations.NOP);
    }

    public static Operation valueOf(byte code) {
        return null;
    }

}
