package com.wolsx.summarize.json.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.beans.IntrospectionException;
import java.util.*;

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

        List<Person> list = new ArrayList<Person>();
        beanTest.setList(list);
        beanTest.getList().add(person);
        Person person2 = new Person();
        person2.setMyname("pER111");
        person2.setXyz(334);
        beanTest.setPerson(person2);
        beanTest.getList().add(person2);
        System.out.println(JSON.toJSONString(beanTest));
        //{"age":12,"intstr":[1,2,3],"list":[{"myname":"pER","xyz":112},{"myname":"pER111","xyz":334}],"name":"王二小","person":{"$ref":"$.list[1]"},"str":["0_abc","1_abcfdfd","2_abcdf"]}


        Person[] arrpersons = new Person[2];
        arrpersons[0] = person;
        arrpersons[1] = person2;
        beanTest.setArrper(arrpersons);
        System.out.println(JSON.toJSONString(beanTest));
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

    @Test
    public void test3()
    {
        String json = "{\"age\":12,\"arrper\":[{\"myname\":\"pER\",\"xyz\":112},{\"myname\":\"pER111\",\"xyz\":334}],\"intstr\":[1,2,3],\"list\":[{\"$ref\":\"$.arrper[0]\"},{\"$ref\":\"$.arrper[1]\"}],\"name\":\"王二小\",\"person\":{\"$ref\":\"$.arrper[1]\"},\"str\":[\"0_abc\",\"1_abcfdfd\",\"2_abcdf\"]}";
        JSONObject jsonObject = JSON.parseObject(json);
        System.out.println(jsonObject);

    }

    @Test
    public void test4()
    {
        String json = "{\"age\":12,\"intstr\":[1,2,3],\"list\":[{\"myname\":\"pER\",\"xyz\":112},{\"myname\":\"pER111\",\"xyz\":334}],\"name\":\"王二小\",\"person\":{\"$ref\":\"$.list[1]\"},\"str\":[\"0_abc\",\"1_abcfdfd\",\"2_abcdf\"]}";
        JSONObject jsonObject = JSON.parseObject(json);
        System.out.println(jsonObject);
        Set<Map.Entry<String, Object>> entries = jsonObject.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.println("key: "+ key + " \t\ttype: "+ value.getClass().toString());
            if(value.getClass().equals(JSONArray.class))
            {
                JSONArray jsonArray = (JSONArray) value;
                int size = jsonArray.size();
                if (size > 0) {
                    Class<?> aClass = jsonArray.get(0).getClass();
                    System.out.println(aClass.toString());
                    if(aClass.equals(JSONObject.class))
                    {
                        JSONObject o = (JSONObject) jsonArray.get(0);

                        Set<String> strings = o.keySet();
                        for(String str:strings)
                        {
                            System.out.println(str);
                            Object o1 = o.get(str);
                            System.out.println(o1.getClass());
                        }
                    }
                }

            }

        }
    }

    @Test
    public void test5()
    {
        Map map = new HashMap<>();

        //name
        ParamDesc paramDesc1 = new ParamDesc();
        paramDesc1.setRequested(true);
        paramDesc1.setType("STRING");
        map.put("name", paramDesc1);

        //age
        ParamDesc paramDesc2 = new ParamDesc();
        paramDesc2.setRequested(true);
        paramDesc2.setType("INT");
        map.put("age", paramDesc2);

        //str
        ParamDesc paramDesc3 = new ParamDesc();
        paramDesc3.setRequested(true);
        paramDesc3.setType("STRINGARRAY");

        map.put("str", paramDesc3);

        //intstr
        ParamDesc paramDesc5 = new ParamDesc();
        paramDesc5.setRequested(true);
        paramDesc5.setType("INTARRAY");

        map.put("intstr", paramDesc5);

        //person
        ParamDesc paramDesc7 = new ParamDesc();
        paramDesc7.setRequested(true);
        paramDesc7.setType("OBJECT");
        Map<String, ParamDesc> map1 = new HashMap<>();
        ParamDesc pd1 = new ParamDesc();
        pd1.setType("STRING");
        pd1.setRequested(true);
        map1.put("myname", pd1);

        ParamDesc pd2 = new ParamDesc();
        pd2.setType("INT");
        pd2.setRequested(true);
        map1.put("xyz", pd2);

        paramDesc7.setFields(map1);
        map.put("person", paramDesc7);



        //list
        ParamDesc paramDesc8 = new ParamDesc();
        paramDesc8.setRequested(true);
        paramDesc8.setType("OBJECTARRAY");
        Map<String, ParamDesc> map2 = new HashMap<>();
        ParamDesc pd3 = new ParamDesc();
        pd3.setType("STRING");
        pd3.setRequested(true);
        map2.put("myname", pd3);

        ParamDesc pd4 = new ParamDesc();
        pd4.setType("INT");
        pd4.setRequested(true);
        map2.put("xyz", pd4);
        paramDesc8.setFields(map2);

        map.put("list", paramDesc8);

        String json = JSON.toJSONString(map);
        System.out.println(json);


    }

    @Test
    public void test6()
    {
        String json = "{\n" +
                "    \"str\": {\n" +
                "        \"requested\": true,\n" +
                "        \"type\": \"STRINGARRAY\"\n" +
                "    },\n" +
                "    \"intstr\": {\n" +
                "        \"requested\": true,\n" +
                "        \"type\": \"INTARRAY\"\n" +
                "    },\n" +
                "    \"person\": {\n" +
                "        \"fields\": {\n" +
                "            \"xyz\": {\n" +
                "                \"requested\": true,\n" +
                "                \"type\": \"INT\"\n" +
                "            },\n" +
                "            \"myname\": {\n" +
                "                \"requested\": true,\n" +
                "                \"type\": \"STRING\"\n" +
                "            }\n" +
                "        },\n" +
                "        \"requested\": true,\n" +
                "        \"type\": \"OBJECT\"\n" +
                "    },\n" +
                "    \"name\": {\n" +
                "        \"requested\": true,\n" +
                "        \"type\": \"STRING\"\n" +
                "    },\n" +
                "    \"list\": {\n" +
                "        \"fields\": {\n" +
                "            \"xyz\": {\n" +
                "                \"requested\": true,\n" +
                "                \"type\": \"INT\"\n" +
                "            },\n" +
                "            \"myname\": {\n" +
                "                \"requested\": true,\n" +
                "                \"type\": \"STRING\"\n" +
                "            }\n" +
                "        },\n" +
                "        \"requested\": true,\n" +
                "        \"type\": \"OBJECTARRAY\"\n" +
                "    },\n" +
                "    \"age\": {\n" +
                "        \"requested\": true,\n" +
                "        \"type\": \"INT\"\n" +
                "    }\n" +
                "}";
        JSONObject jsonObject = JSON.parseObject(json);
        System.out.println(jsonObject);

    }

}
