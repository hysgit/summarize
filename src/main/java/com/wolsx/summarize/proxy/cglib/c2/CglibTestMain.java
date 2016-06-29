package com.wolsx.summarize.proxy.cglib.c2;

import com.sun.org.glassfish.external.statistics.impl.StringStatisticImpl;
import com.wolsx.summarize.proxy.proxywithadvice.Advice;

import java.lang.reflect.Method;

/**
 * Created by hy on 6/29/16.
 */
public class CglibTestMain {
    public static void main(String[] args) {
        Book book = CglibProxyFactory.getProxybyCglib(new Book(), new Advice() {
            @Override
            public void before(Method method) {
                System.out.println("before");
                System.out.println(method.getName());
            }

            @Override
            public void after() {
                System.out.println("after");
            }

            @Override
            public void whenException() {

            }
        });
        book.addBook();
    }
}
