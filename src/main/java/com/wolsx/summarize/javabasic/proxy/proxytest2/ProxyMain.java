package com.wolsx.summarize.javabasic.proxy.proxytest2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by hy on 6/29/16.
 */
public class ProxyMain {
    public static void main(String[] args) {
        CharSequence str = "abcedf";

        CharSequence o = (CharSequence) Proxy.newProxyInstance(ProxyMain.class.getClassLoader(),
                str.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("before call.....");
                        Object retVal = method.invoke(str, args);
                        System.out.println("method called: "+ method.getName());
                        System.out.println("after call......");
                        return retVal;
                    }
                });

        o.hashCode();
        o.charAt(2);
    }
}
