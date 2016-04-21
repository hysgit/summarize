package com.wolsx.summarize.json.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by hy
 * Date: 16-4-21.
 */
public class MainClass {


    @Test
    public void test1()
    {

         // 转成数组转成json

        /**
         * 数组的内容是数字，字符串
         */
        String[] strarr = new String[3];
        strarr[0] = "0_abc";
        strarr[1] = "1_abcfdfd";
        strarr[2] = "2_abcdf";
        Integer[] intarr = new Integer[3];
        intarr[0] = 1;
        intarr[1] = 2;
        intarr[2] = 3;
        String s = JSON.toJSONString(strarr);
        String s1 = JSON.toJSONString(intarr);
        System.out.println(s);  //["0_abc","1_abcfdfd","2_abcdf"]
        System.out.println(s1);//[1,2,3]

        Map map = new HashMap();
        map.put("s", strarr);
        map.put("s1", intarr);
        System.out.println(JSON.toJSONString(map));//{"s":["0_abc","1_abcfdfd","2_abcdf"],"s1":[1,2,3]}

        BeanTest beanTest = new BeanTest();
        beanTest.setName("王二小");
        beanTest.setAge(12);
        beanTest.setStr(strarr);
        beanTest.setIntstr(intarr);
        System.out.println(JSON.toJSONString(beanTest));
        //{"age":12,"intstr":[1,2,3],"name":"王二小","str":["0_abc","1_abcfdfd","2_abcdf"]}
        //值为null的项，不会被输出

        Person person = new Person();
        person.setMyname("pER");
        person.setXyz(112);
        beanTest.setPerson(person);
        System.out.println(JSON.toJSONString(beanTest));
        //{"age":12,"intstr":[1,2,3],"name":"王二小","person":{"myname":"pER","xyz":112},"str":["0_abc","1_abcfdfd","2_abcdf"]}

    }

    @Test
    public void test2() throws IntrospectionException {
        String json = "{\"age\":12,\"intstr\":[1,2,3],\"name\":\"王二小\",\"person\":{\"myname\":\"pER\",\"xyz\":112},\"str\":[\"0_abc\",\"1_abcfdfd\",\"2_abcdf\"]}";

        JSONObject jsonObject = JSON.parseObject(json);
        System.out.println(jsonObject);
        Set<String> strings = jsonObject.keySet();
        System.out.println(strings);
        for (String str : strings) {
            Class<?> aClass = jsonObject.get(str).getClass();
            if (aClass.equals(JSONObject.class)) {
                JSONObject jsonObject1 = jsonObject.getJSONObject(str);

            }
        }





    }

}
