package com.wolsx.summarize.bishiti.typetest;

/**
 * Created by hy on 10/17/16.
 */
public class NULL {

    public static void main(String[] args) {
        ((NULL2)null).haha();
        NULL2 n2 = new NULL2();
        n2.haha();
        NULL2 n3 = null;
        n3.haha();
    }
}

class NULL2 {
    public static void haha(){
        System.out.println("haha");
    }
}
