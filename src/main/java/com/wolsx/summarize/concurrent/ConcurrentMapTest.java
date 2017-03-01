package com.wolsx.summarize.concurrent;

import java.util.Date;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by hy on 9/22/16.
 */
public class ConcurrentMapTest {
    public static void main(String[] args) {
        int num = 100 - 2;
        double step = num*1.0/48;
        for(int i = 0; i < 48; i++){
            System.out.println(((int) ((i + 1) * step)));
        }
    }
}
