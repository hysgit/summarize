package com.wolsx.summarize.bishiti.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK形式的动态代理
 */

interface MustBeImpl {
    void func1();
}

class ImplClass implements MustBeImpl {
    @Override
    public void func1() {
        System.out.println("func1");
    }
}

public class ProxyMain {
    public static void main(String[] args) {
        ImplClass impl = new ImplClass();
        MustBeImpl mustBeImpl = (MustBeImpl) Proxy.newProxyInstance(
                ProxyMain.class.getClassLoader(),
                impl.getClass().getInterfaces(),
                new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return method.invoke(impl, args);
            }
        });
        System.out.println(mustBeImpl.equals(impl));
        System.out.println(impl.equals(mustBeImpl));
    }
}

/**
 1. mustBeImpl对象的类类型是什么？这个类类型和ImplClass的关系是怎样的？

 2. public Object invoke(Object proxy, Method method, Object[] args)中的proxy是什么？method是什么，args是什么？

 3. 在mustBeImpl对象上用方法的时候，实际是怎样的过程？有没有例外的方法？

 4. mustBeImpl.equals(impl),true or false? 为什么？
    impl.equals(mustBeImpl),true or false? 为什么？
 */
