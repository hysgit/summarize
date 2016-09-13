package com.wolsx.summarize.javabasic.proxy.proxywithadvice;

import java.lang.reflect.Method;

/**
 * Created by hy on 6/29/16.
 */
public class MainTest2 {
    public static void main(String[] args) throws InterruptedException {
        String str = "abce";

        Object proxyInstance = ProxyFactory.getProxyInstance(str, new Advice() {
            @Override
            public void before(Method method) {

                System.out.println("method: " + method.getName() + " be called!");
            }

            @Override
            public void after() {

            }

            @Override
            public void whenException() {

            }
        });
        System.out.println(proxyInstance.hashCode());
        proxyInstance.toString();
        proxyInstance.getClass();
       // proxyInstance.notify();
        //proxyInstance.notifyAll();
       // proxyInstance.wait();
       // proxyInstance.wait(1);
       // proxyInstance.wait(1,2);
        System.out.println(proxyInstance.equals(proxyInstance));

        //验证调用hashCode
        CunstomClass c = new CunstomClass();
        Object proxyInstance1 = ProxyFactory.getProxyInstance(c, new Advice() {
            @Override
            public void before(Method method) {
                System.out.println("method: " + method.getName() + " be called!");
            }

            @Override
            public void after() {

            }

            @Override
            public void whenException() {

            }
        });
        System.out.println("----------------");
        System.out.println(proxyInstance1.equals(proxyInstance1));

    }
}
