package com.wolsx.summarize.javabasic.serialize;

import java.io.*;

public class TestSerialversionUID {

    public static void main(String[] args) throws Exception {
        SerializeCustomer();// 序列化Customer对象
        Customer customer = DeserializeCustomer();// 反序列Customer对象
        System.out.println(customer);
    }

    /**
     * MethodName: SerializeCustomer
     * Description: 序列化Customer对象
     *
     * @throws FileNotFoundException
     * @throws IOException
     * @author xudp
     */
    private static void SerializeCustomer() throws FileNotFoundException,
            IOException {
        Customer customer = new Customer("gacl", 25);
        // ObjectOutputStream 对象输出流
        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(
                new File("/tmp/Customer.txt")));
        oo.writeObject(customer);
        System.out.println("Customer对象序列化成功！");
        oo.close();
    }

    /**
     * MethodName: DeserializeCustomer
     * Description: 反序列Customer对象
     *
     * @return
     * @throws Exception
     * @throws IOException
     * @author xudp
     */
    private static Customer DeserializeCustomer() throws Exception, IOException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                new File("/tmp/Customer.txt")));
        Customer customer = (Customer) ois.readObject();
        System.out.println("Customer对象反序列化成功！");
        return customer;
    }
}

/**
 * <p>ClassName: Customer<p>
 * <p>Description: Customer实现了Serializable接口，可以被序列化<p>
 *
 * @author xudp
 * @version 1.0 V
 * @createTime 2014-6-9 下午04:20:17
 */
class Customer implements Serializable {
    //Customer类中没有定义serialVersionUID
    private String name;
    private int age;

    public Customer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /*
     * @MethodName toString
     * @Description 重写Object类的toString()方法
     * @author xudp
     * @return string
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "name=" + name + ", age=" + age;
    }
}
