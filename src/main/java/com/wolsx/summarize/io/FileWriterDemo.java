package com.wolsx.summarize.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by hy
 * Date: 16-4-6.
 * FileWriter和FileReader一样，都没有增加新的方法
 * FileWriter这是一个便利类，方便写字符文件，也就是说方便写字符到字符文件.
 * FileWriter操作的文件，可以是一个存在的文件或者根据平台，在文件不存在的时候自动创建
 * 一般来说，FileWriter需要独占其操作的文件。
 * 这个类是写字符到文件中的，如果要写原始字节，考虑使用FileOutputStream
 * 它的继承体系：
 * java.lang.Object
        java.io.Writer
            java.io.OutputStreamWriter
                java.io.FileWriter
    方法汇总：
         从java.io.Writer继承的方法：
             void write(int c)
             void write(char[] cbuf)
             void write(String str)
             void write(String str, int off, int len)
             Writer append(CharSequence csq)
             Writer append(CharSequence csq, int start, int end)
             Writer append(char c)
        从java.io.OutputStreamWriter继承的方法：
            void write(char[] cbuf, int off, int len) : 在Writer中是抽象方法
            void write(String str, int off, int len)
            void write(int c)
            String getEncoding()
            void flush(): Writer中是抽象方法,字符流必须要把数据从内存刷新到硬盘中，close()方法中也会进行flush()
            void close()：Writer中是抽象方法

    构造函数：
            FileWriter(File file)
            FileWriter(File file, boolean append)
            FileWriter(FileDescriptor fd)
            FileWriter(String fileName)
            FileWriter(String fileName, boolean append)
        这里构造函数的使用和FileReader很类似，不同的地方是，这是写入，FileWriter(File file)会创建新文件，而不管文件是否存在
        如果文件已经存在，会删除原文件。
        如果要保留原来的内容，则要用FileWriter(File file, boolean append)或者FileWriter(String fileName, boolean append)
        如果append为true，则不会删除原文件，而新写入的字符，追加在原文本的后面，如果boolean为false，则和FileWriter(File file)一样
 */
public class FileWriterDemo {
    public static void main(String[] args) throws IOException {

        /**
         *void write(int c)方法 调用的是OutputStreamWriter中的方法，这个方法覆盖了其在Writer中的版本
         */
        FileWriter fw = new FileWriter("/home/hy/workspace/wrksp7/summarize/src/main/java/com/wolsx/summarize/io/fw.txt");
        FileReader fr = new FileReader("/home/hy/workspace/wrksp7/summarize/src/main/java/com/wolsx/summarize/io/summarize.txt");
        int ch = -1;
        while ((ch = fr.read()) != -1) {
            fw.write(ch);//这里参数是int型的，但是写入的时候，并不是写入这个整数对应的字符串，而是该整数对应的字符。
        }
        fw.close();
        fr.close();

        /**
         * 追加类型的构造的使用
         * FileWriter(File file, boolean append)
         */
        fw = new FileWriter("/home/hy/workspace/wrksp7/summarize/src/main/java/com/wolsx/summarize/io/fw.txt", true);
        //经过测试，在使用追加方式的时候，write()会把数据写到文件的最后
        fw.write("abcded");
        fw.close();

        /**
         * write 方法可以写一个int值，也可以写一个char数组，String
         * 而append可以写char，或者CharSequence对象,
         * CharSequence是个接口，顾名思义，这就是个字符序列，
         * 它的实现类有String,StringBuffer,StringBuilder等，也就是说字符串，就是字符序列
         * 通过看append的源代码，可以猜测：
         * 这里不直接写为String，可以多支持几种类型。
         *
         * 字符流写完了一定要flush或者close。
         * 而且出于效率考虑，在写大量字符的时候，每写一定的字符，就要flush一下
         */
        fw = new FileWriter("/home/hy/workspace/wrksp7/summarize/src/main/java/com/wolsx/summarize/io/fw2.txt");
        fw.append('C');
        CharSequence csq = "CharSequence对象!";
        fw.append(csq).append("连续append");
        fw.close();

    }
}
