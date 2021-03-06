package com.wolsx.summarize.proxy.proxytest2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hy on 6/29/16.
 */
public class ProxyFactory {
    public static Object getProxyInstance(Object obj)
    {
        return Proxy.newProxyInstance(ProxyFactory.class.getClassLoader(),
                obj.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("before");
                        Object retVal = method.invoke(obj, args);
                        System.out.println(method.getName()+" be called!");
                        System.out.println("after");

                        return retVal;
                    }
                });
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        List proxyInstance = (List) ProxyFactory.getProxyInstance(list);
        proxyInstance.add("xey");
    }
}
