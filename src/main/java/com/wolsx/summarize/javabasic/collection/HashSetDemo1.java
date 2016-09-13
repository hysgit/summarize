package com.wolsx.summarize.javabasic.collection;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by hy
 * Date: 16-4-24.
 */
public class HashSetDemo1 {
    public static void main(String[] args) {
        HashSet set = new HashSet();
        set.add("a");
        set.add("b");
        set.add("c");
        set.add("d");
        set.add("e");

        Iterator iter = set.iterator();
        while (iter.hasNext()) {
            String value = (String) iter.next();
            System.out.println(value);

        }
        for (Object obj : set) {
            String value = (String) obj;
            System.out.println(value);
        }
    }
}
