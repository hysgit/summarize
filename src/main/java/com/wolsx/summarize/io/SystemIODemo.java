package com.wolsx.summarize.io;

import java.io.InputStream;
import java.io.PrintStream;

/**
 * Created by hy
 * Date: 16-4-22.
 */
public class SystemIODemo {
    public static void main(String[] args) {
        InputStream in = System.in;
        System.out.println(in.getClass());
        PrintStream out = System.out;
        System.out.println(out.getClass());
    }
}
