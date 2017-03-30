package com.wolsx.summarize.other.json.test;

import java.util.List;

/**
 * Created by hy
 * Date: 16-4-21.
 */
public class BeanTest {
    private String name;
    private Integer age;
    private String[] str;
    private Integer[] intstr;
    private Person person;
    private List<Person> list;
    private Person[] arrper;


    public List<Person> getList() {
        return list;
    }

    public void setList(List<Person> list) {
        this.list = list;
    }

    public Person[] getArrper() {
        return arrper;
    }

    public void setArrper(Person[] arrper) {
        this.arrper = arrper;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String[] getStr() {
        return str;
    }

    public void setStr(String[] str) {
        this.str = str;
    }

    public Integer[] getIntstr() {
        return intstr;
    }

    public void setIntstr(Integer[] intstr) {
        this.intstr = intstr;
    }
}
