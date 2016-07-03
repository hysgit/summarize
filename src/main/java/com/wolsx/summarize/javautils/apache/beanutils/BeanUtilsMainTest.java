package com.wolsx.summarize.javautils.apache.beanutils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;

/**
 * Created by hy on 7/2/16.
 */
public class BeanUtilsMainTest {
    public static void main(String[] args) throws Exception {
        Bean2 bean2 = new Bean2();
        bean2.setId(1);
        bean2.setAddr("addr");
        bean2.setAge(10);
        bean2.setName("abcdef");
        Object o = BeanUtils.cloneBean(bean2);
        System.out.println(o);

        Bean1 bean1 = new Bean1();
        BeanUtils.copyProperties(bean1,bean2);
        System.out.println(bean1);

        //获取到属性名和属性值的键值对
        Map describe = BeanUtils.describe(bean1);
        System.out.println(describe);

        Map cache = BeanUtils.createCache();
        System.out.println(cache);

        String property = BeanUtils.getProperty(bean1, "name");
        String simpleProperty = BeanUtils.getSimpleProperty(bean1, "name");
        System.out.println("property: "+ property);
        System.out.println("simpleProperty: "+ simpleProperty);

        Map<String, Object> map = new HashMap<>();
        map.put("name","abcedf1");
        map.put("age", 12);
        bean1 = new Bean1();
        BeanUtils.populate(bean1,map);      //把map中的键值对，复制到bean中
        System.out.println(bean1);

    }
}
