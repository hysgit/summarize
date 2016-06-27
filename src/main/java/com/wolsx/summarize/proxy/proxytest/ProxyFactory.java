package com.wolsx.summarize.proxy.proxytest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by hy on 6/27/16.
 */
public class ProxyFactory {

    public static <T> Object getProxyIntance(T obj, Advice advice)
    {
        return Proxy.newProxyInstance(ProxyFactory.class.getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                String name = method.getName();
                advice.before();
                System.out.println(name);
//                System.out.println("proxy's Class： "+proxy.getClass());
                Object invoke = method.invoke(obj, args);

                advice.after();
                return invoke;
            }
        });
    }
}
