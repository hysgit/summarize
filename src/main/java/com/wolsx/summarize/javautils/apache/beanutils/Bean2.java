package com.wolsx.summarize.javautils.apache.beanutils;

/**
 * Created by hy on 7/2/16.
 */
public class Bean2 extends Bean1 {
    private String addr;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }


    @Override
    public String toString() {
        return "Bean2{" +
                "id='"+super.getId()+"'"+
                ",name='"+super.getName()+"'"+
                ",age='"+super.getAge()+"'"+
                ",addr='" + addr + '\'' +
                '}';
    }
}
