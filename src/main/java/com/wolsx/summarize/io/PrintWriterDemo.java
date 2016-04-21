package com.wolsx.summarize.io;

import java.io.OutputStreamWriter;
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
     * PrintWriter的构造函数：
     * PrintWriter(Writer out)
     * PrintWriter(Writer out, boolean autoFlush)
     * PrintWriter(OutputStream out)
     * PrintWriter(OutputStream out, boolean autoFlush)
     * PrintWriter(String fileName)
     * PrintWriter(String fileName, String csn)
     * PrintWriter(File file)
     * PrintWriter(File file, String csn)
     * 从构造函数可以看出，必须为PrintWriter对象的构建提供一个输出流，或者是可以用来构建输出流的对象。
     * 和OutputStreamWriter对象一样，虽然也是继承于Writer类，是属于字符流，但是，它也只是对字节流的包装,和OutputStreamWriter
     * 不同的是，它还不仅能直接传入OutputStream对象，还能传入Writer对象。
     * PrintWriter的功能，相对于OutputStreamWriter来说，增强了很多,特别地说，PrintWriter并不是继承自OutputStreamWriter，
     * 而是和OutputStreamWriter一样继承自Writer
     * 构造函数分为4类，每类2种：
     *      从Writer对象构建
     *      从OutputStream对象构建
     *      从文件名构建
     *      从文件对象构建
     * 从Writer对象构建，因为Writer是抽象类，也就是说，用Writer其他子类的对象来构建
     * PrintWriter类实际上是一个包装类，它内部有一个域，就是Writer对象，传进去的Writer对象，直接设置给了这个域
     * 而如果传进去的是OutputStream对象，则用OutputStreamWriter,和BufferedWriter,把字节输出流，转为字符流，再设置给
     * PrintWriter类中的Writer域。
     */
    OutputStreamWriter out = new OutputStreamWriter(System.out);
    PrintWriter pw = new PrintWriter(out);
}
