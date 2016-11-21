package com.wolsx.summarize.bishiti.exceptioncheck;

/**
 * 请写出以下代码的输出
 */
public class TryCatchFinally {
    public static void main(String[] args) {
        System.out.println(getInt());
    }

    public static int getInt()
    {
        try {
            System.out.println("a");
            int i = 1/0;
            System.out.println("b");
            return 1;
        } catch (Exception e) {
            System.out.println("c");
            return 2;
        }
        finally {
            return 3;
        }
    }
}
