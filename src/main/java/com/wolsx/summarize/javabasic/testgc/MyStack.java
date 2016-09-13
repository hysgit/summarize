package com.wolsx.summarize.javabasic.testgc;

import java.util.EmptyStackException;

/**
 * Created by hy on 8/27/16.
 */
public class MyStack {
    private Object[] elements;
    private int Increment = 10;
    private int size = 0;

    public MyStack(int size) {
        elements = new Object[size];
    }

    //入栈
    public void push(Object o) {
        capacity();
        elements[size++] = o;
    }

    //出栈
    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        return elements[--size];
    }

    //增加栈的容量
    private void capacity() {
        if (elements.length != size)
            return;
        Object[] newArray = new Object[elements.length + Increment];
        System.arraycopy(elements, 0, newArray, 0, size);
    }

    public static void main(String[] args) throws InterruptedException {
        pushpop();
        System.gc();
        int x = 0;
        for(;x < 1000;x++){
            Thread.sleep(1000);
        }
    }

    private static void pushpop() {
        MyStack stack = new MyStack(100);
        for (int i = 0; i < 100; i++)
            stack.push(new GCobject(i));
        for (int i = 0; i < 100; i++) {
            System.out.println(stack.pop().toString());
        }
        GCobject gCobject = new GCobject(1000);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("before: MyStack对象被析构:");
        super.finalize();
        System.out.println("after: MyStack对象被析构:");
    }
}
