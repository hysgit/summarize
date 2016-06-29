package com.wolsx.summarize.proxy.proxywithadvice;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hy on 6/29/16.
 */
public class MainTest {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();

        Map proxyInstance = (Map) ProxyFactory.getProxyInstance(map, new Advice() {
            @Override
            public void before(Method method) {
                System.out.println("before");
                System.out.println("method: " + method.getName() + " be called!");
            }

            @Override
            public void after() {
                System.out.println("after");
            }

            @Override
            public void whenException() {
                System.out.println("when excepiton");
            }
        });
        proxyInstance.hashCode();
        proxyInstance.put("ab", 'c');
        proxyInstance.size();
        proxyInstance.clear();
    }
}
