package com.wolsx.summarize.bishiti.typetest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hy on 10/17/16.
 * 在Java中，局部变量如果没有初始化，或者在后面可能会没有被赋值，那么程序将不会编译通过
 */
class Person {
    public Person(Integer id, String name)
    {
        this.id = id;
        this.name = name;
    }
    public Integer id;
    public String name;
}
public class UnInitTest {
    public static void main(String[] args) {
        Map<String, Person> mapPerson = new HashMap<>();
        Person p1= new Person(1, "Jerry");
        mapPerson.put("person", p1);
        p1.name = "Tom";
        System.out.println(mapPerson.get("person").name);
    }
}
