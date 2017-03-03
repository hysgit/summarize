package com.wolsx.summarize.javabasic.classloader;

import com.wolsx.summarize.javabasic.calendar.CalendarTest;
import org.junit.Test;

/**
 * Created by hy on 7/14/16.
 */
public class TestClassLoader {

    @Test
    public void test1()
    {



        ClassLoader classLoader = String.class.getClassLoader();
        ClassLoader classLoader1 = Integer.class.getClassLoader();
        ClassLoader classLoader2 = TestClassLoader.class.getClassLoader();
        ClassLoader classLoader3 = CalendarTest.class.getClassLoader();
        System.out.println(classLoader);
        System.out.println(classLoader1);
        System.out.println(classLoader2);
        System.out.println(classLoader3);

    }
}
