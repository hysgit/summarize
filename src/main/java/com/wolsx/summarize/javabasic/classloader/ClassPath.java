package com.wolsx.summarize.javabasic.classloader;

import java.net.URL;

/**
 * Created by hy on 9/1/16.
 */
public class ClassPath {
    public static void main(String[] args) {
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i].toExternalForm());
        }

        System.out.println(System.getProperty("sun.boot.class.path"));
    }
}
