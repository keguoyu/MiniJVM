package com.keguoyu.minijvm.operation;

import java.util.HashMap;
import java.util.Map;

public class OperationFactory {

    private static final Map<Byte, Operation> map = new HashMap<>(256);

    static {
        map.put((byte) 0x00, ConstOperations.NOP);
        map.put((byte) 0x01, ConstOperations.ACONST_NULL);
        map.put((byte) 0x02, ConstOperations.ICONST_M1);
        map.put((byte) 0x03, ConstOperations.ICONST_0);
        map.put((byte) 0x04, ConstOperations.ICONST_1);
        map.put((byte) 0x05, ConstOperations.ICONST_2);
        map.put((byte) 0x06, ConstOperations.ICONST_3);
        map.put((byte) 0x07, ConstOperations.ICONST_4);
        map.put((byte) 0x08, ConstOperations.ICONST_5);
        map.put((byte) 0x09, ConstOperations.LCONST_0);
        map.put((byte) 0x0a, ConstOperations.LCONST_1);
        map.put((byte) 0x0b, ConstOperations.FCONST_0);
        map.put((byte) 0x0c, ConstOperations.FCONST_1);
        map.put((byte) 0x0d, ConstOperations.FCONST_2);
        map.put((byte) 0x0e, ConstOperations.DCONST_0);
        map.put((byte) 0x0f, ConstOperations.DCONST_1);
        map.put((byte) 0x10, PushOperations.BI_PUSH);
        map.put((byte) 0x11, PushOperations.SI_PUSH);
        map.put((byte) 0x15, LoadOperations.ILOAD);
        map.put((byte) 0x16, LoadOperations.LLOAD);
        map.put((byte) 0x17, LoadOperations.FLOAD);
        map.put((byte) 0x18, LoadOperations.DLOAD);
        map.put((byte) 0x19, LoadOperations.ALOAD);
        map.put((byte) 0x1a, LoadOperations.ILOAD_0);
        map.put((byte) 0x1b, LoadOperations.ILOAD_1);
        map.put((byte) 0x1c, LoadOperations.ILOAD_2);
        map.put((byte) 0x1d, LoadOperations.ILOAD_3);
        map.put((byte) 0x1e, LoadOperations.LLOAD_0);
        map.put((byte) 0x1f, LoadOperations.LLOAD_1);
        map.put((byte) 0x20, LoadOperations.LLOAD_2);
        map.put((byte) 0x21, LoadOperations.LLOAD_3);
        map.put((byte) 0x22, LoadOperations.FLOAD_0);
        map.put((byte) 0x23, LoadOperations.FLOAD_1);
        map.put((byte) 0x24, LoadOperations.FLOAD_2);
        map.put((byte) 0x25, LoadOperations.FLOAD_3);
        map.put((byte) 0x26, LoadOperations.DLOAD_0);
        map.put((byte) 0x27, LoadOperations.DLOAD_1);
        map.put((byte) 0x28, LoadOperations.DLOAD_2);
        map.put((byte) 0x29, LoadOperations.DLOAD_3);
        map.put((byte) 0x2a, LoadOperations.ALOAD_0);
        map.put((byte) 0x2b, LoadOperations.ALOAD_1);
        map.put((byte) 0x2c, LoadOperations.ALOAD_2);
        map.put((byte) 0x2e, LoadOperations.IALOAD);
        map.put((byte) 0x2f, LoadOperations.LALOAD);
        map.put((byte) 0x30, LoadOperations.FALOAD);
        map.put((byte) 0x31, LoadOperations.DALOAD);
        map.put((byte) 0x32, LoadOperations.AALOAD);
        map.put((byte) 0x33, LoadOperations.BALOAD);
        map.put((byte) 0x34, LoadOperations.CALOAD);
        map.put((byte) 0x35, LoadOperations.SALOAD);
        map.put((byte) 0x36, StoreOperations.ISTORE);
        map.put((byte) 0x37, StoreOperations.LSTORE);
        map.put((byte) 0x38, StoreOperations.FSTORE);
        map.put((byte) 0x39, StoreOperations.DSTORE);
        map.put((byte) 0x3a, StoreOperations.ASTORE);
        map.put((byte) 0x3b, StoreOperations.ISTORE_0);
        map.put((byte) 0x3c, StoreOperations.ISTORE_1);
        map.put((byte) 0x3d, StoreOperations.ISTORE_2);
        map.put((byte) 0x3e, StoreOperations.ISTORE_3);
        map.put((byte) 0x3f, StoreOperations.LSTORE_0);
        map.put((byte) 0x40, StoreOperations.LSTORE_1);
        map.put((byte) 0x41, StoreOperations.LSTORE_2);
        map.put((byte) 0x42, StoreOperations.LSTORE_3);
        map.put((byte) 0x43, StoreOperations.FSTORE_0);
        map.put((byte) 0x44, StoreOperations.FSTORE_1);
        map.put((byte) 0x45, StoreOperations.FSTORE_2);
        map.put((byte) 0x46, StoreOperations.FSTORE_3);
    }

    public static Operation valueOf(byte code) {
        return map.get(code);
    }

}
