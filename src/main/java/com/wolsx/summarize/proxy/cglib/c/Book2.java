package com.wolsx.summarize.proxy.cglib.c;

/**
 * Created by hy on 6/29/16.
 */
public class Book2 {
    public void addBook() {
        System.out.println("增加图书的普通方法...");
    }

    public final void dispaly()
    {
        System.out.println("final function!");
    }
}
