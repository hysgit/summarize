package com.wolsx.summarize.proxy.zxx2010;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;

/**
 * Created by hy on 7/14/16.
 */
public class ProxyTest2 {
    public static void main(String[] args) {
        Proxy.newProxyInstance(ProxyTest2.class.getClassLoader(), Collection.class.getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return null;
                    }
                });
    }
}
