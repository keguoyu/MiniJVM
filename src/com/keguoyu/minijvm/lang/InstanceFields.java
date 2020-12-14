package com.keguoyu.minijvm.lang;

public class InstanceFields {
    private final Object[] instanceVals;

    public InstanceFields(int num) {
        instanceVals = new Object[num];
    }

    public void setInstanceValsAt(int position, Object val) {
        instanceVals[position] = val;
    }

    public Object getInstanceValAt(int position) {
        return instanceVals[position];
    }

}
