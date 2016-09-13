package com.wolsx.summarize.javabasic.proxy.cglib.c2;

import com.wolsx.summarize.javabasic.proxy.proxywithadvice.Advice;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by hy on 6/29/16.
 */
public class CglibProxyFactory {

    public static <T> T getProxybyCglib(T target, Advice advice)
    {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        // 回调方法
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                advice.before(method);
                Object invoke = method.invoke(target, args);
                advice.after();
                return invoke;
            }
        });
        // 创建代理对象
        return (T) enhancer.create();
    }
}
