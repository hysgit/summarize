package com.wolsx.summarize.javabasic.encryption;

/**
 * 测试网关中用的MD5加密
 * 加密方式: 先进行一次MD5加密,然后 把结果倒序,再进行一次MD5加密
 * Created by hy on 7/29/16.
 */
public class EncryptionMain {
    public static void main(String[] args) {
        String test = AppUtils.enCodePassword("test");
        System.out.println(test);
    }
}
