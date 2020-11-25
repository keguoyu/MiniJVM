package com.keguoyu.minijvm.pojo;

/**
 * @author keguoyu
 * @version 1.0
 **/
public class Args {
    public boolean isRightFmt = true;     //是否是正确的格式;
    public boolean helpFlag;        //是否是help 查看帮助
    public boolean versionFlag;    //是否是查看版本
    public String cpOption = "";  //classPath 的路径;          java -cp(-classpath) xxx
    public String clazz;  //要编译的class 文件;
    public String[] args; //执行clazz文件需要的参数
    public String xJreOption;

    public Args(String[] args){
        parseCmd(args);
    }

    public void parseCmd(String[] args) {
        int index = 1;

        if (args.length<2){
            isRightFmt = false;
            return;
        }
        if (!args[0].equals("java")) {
            isRightFmt = false;
        } else {
            switch (args[1]) {
                case "-help":
                case "-?":
                    helpFlag = true;
                    break;
                case "-version":
                    versionFlag = true;
                    break;
                case "-cp":
                case "classpath":
                    if (args.length < 4) {
                        isRightFmt = false;
                    }
                    index = 4;
                    this.cpOption = args[2];
                    break;
                case "-Xjre":
                    if (args.length < 4) {
                        isRightFmt = false;
                    }
                    index = 4;
                    this.xJreOption = args[2];
                    break;
            }

            this.clazz = args[index - 1];
            this.args = new String[args.length - index];
            System.arraycopy(args, index, this.args, 0, args.length - index);
        }
    }

    public void printUsage() {
        System.out.println("Usage: java [-options] class [args...]\n");
    }
}
