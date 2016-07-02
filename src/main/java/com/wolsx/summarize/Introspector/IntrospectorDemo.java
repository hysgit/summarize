package com.wolsx.summarize.Introspector;

import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 * Created by hy
 * Date: 16-4-19.
 *
 * 测试内省功能
 */
public class IntrospectorDemo {

    @Test
    public void test1()
    {
        /**
         * 内省，就是分析一个类的属性
         * 可以把一个类的所有属性都取出来，封装在一个BeanInfo对象中
         *  用BeanInfo的getPropertyDescriptors()方法，获取到所有属性的属性描述器的数组
         *  属性描述器，就是描述了一个属性的各种信息
         */
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor pd : pds) {
                System.out.println(pd.getName());
            }

            /**
             * 前面一个方法，会把继承来的属性一并解析出来，比如class属性，而下面的这个方法，
             * 可以指定在内省的时候，在继承树上哪个类开始停止解析属性。
             * 比如以下的例子，会忽略从Object类继承下来的属性。
             */
            System.out.println("------------------------------------");
            BeanInfo beanInfo2 = Introspector.getBeanInfo(Person.class, Object.class);
            pds = beanInfo2.getPropertyDescriptors();
            for (PropertyDescriptor pd : pds) {
                System.out.println(pd.getName());
            }

        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }
}
