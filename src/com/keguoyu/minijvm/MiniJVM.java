package com.keguoyu.minijvm;

import com.keguoyu.minijvm.jvm.JavaVirtualMachine;
import com.keguoyu.minijvm.pojo.Args;

/**
 * @author keguoyu
 * @version 1.0
 **/
public class MiniJVM {

    public static void main(String[] args) {
        Args argv = new Args(args);
        if (!argv.isRightFmt || argv.helpFlag || argv.args == null) {
            argv.printUsage();
        } else if (argv.versionFlag) {
            System.out.println("MiniJVM Version 1.0");
        } else {
            JavaVirtualMachine jvm = new JavaVirtualMachine(argv.cpOption, argv.clazz);
            jvm.start();
        }
    }

}
