package com.wolsx.summarize.proxy.proxytest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by hy on 6/27/16.
 */
public class MainTest {
    public static void main(String[] args) {
        String str = "abde";
        CharSequence proxyIntance = (CharSequence)ProxyFactory.getProxyIntance(str, new Advice() {
            @Override
            public void before() {
                System.out.println(">>>before: str is :" + str);
            }

            @Override
            public void after() {
                System.out.println("<<<after: str is :" + str);
            }
        });
        Class<CharSequence> charSequenceClass = CharSequence.class;
        Method[] methods = charSequenceClass.getMethods();
        for (Method method : methods) {
            try {
                method.invoke(proxyIntance,null);
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }

    }
}
