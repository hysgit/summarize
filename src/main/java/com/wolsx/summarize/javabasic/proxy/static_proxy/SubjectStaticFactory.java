package com.wolsx.summarize.javabasic.proxy.static_proxy;

/**
 * Created by hy on 6/27/16.
 * 生成静态代理类工厂
 */
public class SubjectStaticFactory {
    //客户类调用此工厂方法获得代理对象。
    //对客户类来说，其并不知道返回的是代理类对象还是委托类对象。
    public static Subject getInstance(){
        return new ProxySubject(new RealSubject());
    }
}