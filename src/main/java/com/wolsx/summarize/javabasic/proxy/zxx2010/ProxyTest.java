package com.wolsx.summarize.javabasic.proxy.zxx2010;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by hy on 16-6-25.
 */
public class ProxyTest
{
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazzProxy1 = Proxy.getProxyClass(Collection.class.getClassLoader(),
                Collection.class);

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

        System.out.println("---------------------------------------------------");

        Method[] methods = clazzProxy1.getMethods();
        for (Method method : methods) {
            String name = method.getName();
            Class[] parameterTypes = method.getParameterTypes();
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

        Constructor constructor = clazzProxy1.getConstructor(InvocationHandler.class);
        Collection<String> c = new ArrayList<>();

        Collection o = (Collection) constructor.newInstance(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method.getName());
                Object obj = method.invoke(c, args);
                System.out.println(obj);
                System.out.println();
                return obj;
            }
        });
        o.add("ass");
        System.out.println(o.size());


    }
}
