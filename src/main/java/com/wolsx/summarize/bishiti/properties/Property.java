package com.wolsx.summarize.bishiti.properties;

/**
 * 在Javabean的概念上，这个类有几个属性？分别是什么？
 */
public class Property {
    private Integer id;
    private String name;
    private Integer age;
    private String addr;

    public void setSex(String sex) {
    }

    public String getName() {
        return name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
