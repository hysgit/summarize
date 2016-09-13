package com.wolsx.summarize.javabasic.proxy.cglib.c1;

/**
 * Created by hy on 6/29/16.
 */
public class TestCglib {

    public static void main(String[] args) {
        BookFacadeCglib cglib=new BookFacadeCglib();
        BookFacade bookCglib=(BookFacade)cglib.getInstance(new BookFacade());
        bookCglib.addBook();
        System.out.println(bookCglib.getClass());
    }
}
