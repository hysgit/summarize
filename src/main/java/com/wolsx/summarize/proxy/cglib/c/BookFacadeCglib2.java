package com.wolsx.summarize.proxy.cglib.c;

import com.wolsx.summarize.proxy.cglib.c2.Book;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by hy on 6/29/16.
 */
public class BookFacadeCglib2 {
    public static Object getInstance(Object target) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        // 回调方法
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("事物开始");
                proxy.invokeSuper(obj, args);
                System.out.println("事物结束");
                return null;
            }
        });
        // 创建代理对象
        return enhancer.create();
    }

    public static void main(String[] args) {

        Book2 book2 = (Book2) getInstance(new Book2());
        book2.addBook();
        book2.dispaly();
    }
}
