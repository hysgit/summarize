package com.wolsx.summarize.proxy.static_proxy;

/**
 * Created by hy on 6/27/16.
 */
public class Client1 {
    public static void main(String[] args) {
        Subject proxy = SubjectStaticFactory.getInstance();
        proxy.dealTask("DBQueryTask");
    }
}
