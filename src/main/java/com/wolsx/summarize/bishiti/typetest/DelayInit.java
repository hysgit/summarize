package com.wolsx.summarize.bishiti.typetest;

/**
 * Created by hy on 10/18/16.
 */
class Delay{
    private final int a;
    private static final int b;
    private  final int c;

    static {
        b = 1;
    }

//    {
//        c = 1;
//    }

    public Delay(){
        a = 1;
        c = 1;
    }

    public int getA() {
        return a;
    }
}

public class DelayInit {
    public static void main(String args[]) {
        System.out.println("a"+1);
    }
}
