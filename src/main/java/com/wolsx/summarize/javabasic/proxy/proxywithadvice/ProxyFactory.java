package com.wolsx.summarize.javabasic.proxy.proxywithadvice;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by hy on 6/29/16.
 */
public class ProxyFactory {
    public static Object getProxyInstance(Object obj, Advice advice)
    {
        return Proxy.newProxyInstance(ProxyFactory.class.getClassLoader(),
                obj.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        Object retVal=null;
                        advice.before(method);
                        try {
                             retVal = method.invoke(obj, args);
                            if(method.getName().equals("hashCode"))
                            {
                                System.out.println(obj.hashCode());
                            }
                        }
                        catch (Exception e)
                        {
                            advice.whenException();
                        }

                        advice.after();
                        return retVal;
                    }
                });
    }
}
