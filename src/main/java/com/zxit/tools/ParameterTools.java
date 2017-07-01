package com.zxit.tools;


public class ParameterTools {
    public static String space(int len) {
        StringBuffer buffer = new StringBuffer();

        for (int i = 0; i < len; i++) {
            buffer.append(" ");
        }
        return buffer.toString();
    }

    public static String fill(int len, char ch) {
        StringBuffer buffer = new StringBuffer();

        for (int i = 0; i < len; i++) {
            buffer.append(ch);
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        ParameterTools pt = new ParameterTools();

        char ch = 'd';
        System.out.println(fill(3, 'd'));
    }
}