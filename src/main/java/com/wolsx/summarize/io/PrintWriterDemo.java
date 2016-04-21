package com.wolsx.summarize.io;

import java.io.PrintWriter;

/**
 * Created by hy
 * Date: 16-4-21.
 */
public class PrintWriterDemo {
    /**
     * PrintWriter这个类是Writer的子类
     * 较通用的流类的构造函数的基本特征是，他们都没有默认的构造函数，
     * 因为，它们操作的对象都不能是默认的，而是要在构建对象的时候，给这个流直接或者间接地指定要操作的流
     *
     * PrintWriter的构造函数：
     *          PrintWriter (Writer out)
     *
     */
    PrintWriter pw = new PrintWriter();
}
