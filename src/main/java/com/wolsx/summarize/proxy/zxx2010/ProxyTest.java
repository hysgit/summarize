package com.wolsx.summarize.proxy.zxx2010;

import java.lang.reflect.Constructor;
import java.lang.reflect.Proxy;
import java.util.Collection;

/**
 * Created by hy on 16-6-25.
 */
public class ProxyTest
{
    public static void main(String[] args) {
        Class clazzProxy1 = Proxy.getProxyClass(Collection.class.getClassLoader(),
                Collection.class);
        System.out.println(clazzProxy1.getName()+" "+clazzProxy1.getSimpleName()+" "+clazzProxy1.getTypeName());
        Constructor[] constructors = clazzProxy1.getConstructors();
        for (Constructor constructor : constructors) {
            String name = constructor.getName();
            Class[] parameterTypes = constructor.getParameterTypes();
            StringBuilder sb = new StringBuilder();
            sb.append(name).append("(");
            for (Class clazzParam : parameterTypes) {
                sb.append(clazzParam.getName()).append(",");
            }
            if(parameterTypes.length!=0)
            {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(")");
            System.out.println(sb.toString());
        }
    }
}
