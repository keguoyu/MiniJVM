package com.keguoyu.minijvm.utils;

public class Debugger {

    private static boolean debug = true;

    public static void setDebug(boolean debug) {
        Debugger.debug = debug;
    }

    public static void println(Object obj) {
        if (debug) {
            System.out.println(obj);
        }
    }

    public static void printlnError(Throwable throwable) {
        if (debug) {
            System.err.println(throwable.getMessage());
        }
    }

    public static void printf(String format, Object...objects) {
        if (debug) {
            System.out.printf(format, objects);
        }
    }

}
