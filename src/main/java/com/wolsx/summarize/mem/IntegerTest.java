package com.wolsx.summarize.mem;

import java.io.*;
import java.util.Date;

/**
 * Created by hy on 11/23/16.
 */
public class IntegerTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Pabc pabc = new Pabc();
        Date date = new Date();
        pabc.setA(1000);
        pabc.setB("abc");
        pabc.setC(new Date());

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteArrayOutputStream);
        out.writeObject(pabc);

        ByteArrayInputStream bis = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream in = new ObjectInputStream(bis);
        Pabc pabc2 = (Pabc) in.readObject();
        pabc.setB("bbbbb");
        pabc.setA(999999);
        pabc.setC(new Date(date.getTime()+98987));

        System.out.println(pabc);
        System.out.println(pabc2);
    }
}

class Pabc implements Serializable{
    private Integer a;
    private String b;
    private Date c;

    @Override
    public String toString() {
        return "Pabc{" +
                "c=" + c +
                ", b='" + b + '\'' +
                ", a=" + a +
                '}';
    }

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public Date getC() {
        return c;
    }

    public void setC(Date c) {
        this.c = c;
    }
}
