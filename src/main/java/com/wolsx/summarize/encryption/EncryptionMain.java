package com.wolsx.summarize.encryption;

/**
 * Created by hy on 7/29/16.
 */
public class EncryptionMain {
    public static void main(String[] args) {
        String test = AppUtils.enCodePassword("test");
        System.out.println(test);
    }
}
