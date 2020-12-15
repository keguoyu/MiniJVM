package com.keguoyu.minijvm.utils;

/**
 * 符号引用
 */
public class TypesConverter {

    public static String parse(String rawType) {
        int index = rawType.indexOf(")");
        if (index != -1) {
            String parameterType =  parse(rawType, 0, index + 1);
            String returnType = parse(rawType, index + 1, rawType.length());
            return parameterType + ", " + returnType;
        }
        return "";
    }

    private static String parse(String rawType, int from, int to) {
        int rawFrom = from;
        StringBuilder var5 = new StringBuilder();
        int var6 = 0;

        while(rawFrom < to) {
            String var7;
            switch(rawType.charAt(rawFrom++)) {
                case '(':
                    var5.append('(');
                    continue;
                case ')':
                    var5.append(')');
                    continue;
                case '*':
                case '+':
                case ',':
                case '-':
                case '.':
                case '/':
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                case ':':
                case ';':
                case '<':
                case '=':
                case '>':
                case '?':
                case '@':
                case 'A':
                case 'E':
                case 'G':
                case 'H':
                case 'K':
                case 'M':
                case 'N':
                case 'O':
                case 'P':
                case 'Q':
                case 'R':
                case 'T':
                case 'U':
                case 'W':
                case 'X':
                case 'Y':
                default:
                    throw new RuntimeException("fuuuu");
                case 'B':
                    var7 = "byte";
                    break;
                case 'C':
                    var7 = "char";
                    break;
                case 'D':
                    var7 = "double";
                    break;
                case 'F':
                    var7 = "float";
                    break;
                case 'I':
                    var7 = "int";
                    break;
                case 'J':
                    var7 = "long";
                    break;
                case 'L':
                    int var9 = rawType.indexOf(59, rawFrom);
                    if (var9 == -1) {
                        throw new RuntimeException("ffffff");
                    }

                    var7 = rawType.substring(rawFrom, var9).replace('/', '.');
                    rawFrom = var9 + 1;
                    break;
                case 'S':
                    var7 = "short";
                    break;
                case 'V':
                    var7 = "void";
                    break;
                case 'Z':
                    var7 = "boolean";
                    break;
                case '[':
                    ++var6;
                    continue;
            }

            if (var5.length() > 1 && var5.charAt(0) == '(') {
                var5.append(", ");
            }

            var5.append(var7);

            while(var6 > 0) {
                var5.append("[]");
                --var6;
            }
        }

        return var5.toString();
    }
}
