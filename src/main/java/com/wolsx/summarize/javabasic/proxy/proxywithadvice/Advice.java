package com.wolsx.summarize.javabasic.proxy.proxywithadvice;

import java.lang.reflect.Method;

/**
 * Created by hy on 6/29/16.
 */
public interface Advice {
    public void before(Method method);
    public void after();
    public void whenException();
}
