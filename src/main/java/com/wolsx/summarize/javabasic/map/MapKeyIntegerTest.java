package com.wolsx.summarize.javabasic.map;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hy on 12/26/16.
 */
public class MapKeyIntegerTest {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        Integer ikey1 = new Integer(1);
        Integer ikey2 = new Integer(1);
        map.put(ikey1,"ikey1");
        map.put(ikey2, "ikey2");
        System.out.println(map);
    }
}
