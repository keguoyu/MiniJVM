package com.keguoyu.minijvm.utils;

import java.util.HashMap;
import java.util.Map;

public class ArrayTypes {
    public static final int ARRAY_BOOLEAN =4;
    public static final int ARRAY_CHAR =5;
    public static final int ARRAY_FLOAT =6;
    public static final int ARRAY_DOUBLE =7;
    public static final int ARRAY_BYTE =8;
    public static final int ARRAY_SHORT =9;
    public static final int ARRAY_INT = 10;
    public static final int ARRAY_LONG = 11;

    public static Map<String, String>  primitiveTypes = new HashMap<>();

    static {
        primitiveTypes.put("void",   "V");
        primitiveTypes.put("boolean",   "Z");
        primitiveTypes.put("byte",   "B");
        primitiveTypes.put("short",   "S");
        primitiveTypes.put("int",   "I");
        primitiveTypes.put("long",   "J");
        primitiveTypes.put("char",   "C");
        primitiveTypes.put("float",   "F");
        primitiveTypes.put("double",   "D");
    }
}
