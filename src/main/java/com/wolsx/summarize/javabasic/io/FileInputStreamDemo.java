package com.wolsx.summarize.javabasic.io;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by hy
 * Date: 16-4-7.
 */
public class FileInputStreamDemo {
    public static void main(String[] args) throws IOException {

        /**
         * FileInputStream： 这个类是字节流操作文件的类,对应到字符流是FileReader类, 但是和字符流不同的是，它是直接继承自
         *                  顶层类：InputStream
         *
         * 继承关系：
         *  java.lang.Object
                java.io.InputStream
                    java.io.FileInputStream
         * 构造函数：
         *      FileInputStream(File file)
         *      FileInputStream(FileDescriptor fdObj)
         *      FileInputStream(String name)
         *
         * 方法：
         *      int available() 覆盖InputStream的方法
         *              这个方法返回的是，从这个流中读取字节的时候，可以不受阻塞地读取或者可以跳过的字节数。
         *              也就是说，这个方法返回，字节流中已经准备好的字节数，可以立刻读取到不用等待的字节数，
         *      void close()   关闭打开的流，并且释放该流占用的资源 覆盖InputStream的方法
         *      protected void finalize()
         *      FileChannel	getChannel()    属于nio，暂且不论
         *      FileDescriptor getFD()  获得该流所关联的文件的文件描述符
         *      int	read()      实现InputStream中的方法
         *      int	read(byte[] b)  覆盖InputStream的方法
         *      int	read(byte[] b, int off, int len) 覆盖InputStream的方法
         *      long skip(long n)   覆盖InputStream的方法
         * 继承的方法：
         *      java.io.InputStream
         *          void	mark(int readlimit)
         *          boolean	markSupported()
         *          void	reset()
         *
         */

        /**
         * 构造方法：FileInputStream(String name)
         * 其他几个构造方法不再测试
         * 测试available()方法
         */
        String filePath =
                "/home/hy/workspace/wrksp7/summarize/src/main/java/com/wolsx/summarize/io/txt/summarize.txt";
        FileInputStream fis = new FileInputStream(filePath);
        int available = fis.available();
        System.out.println(available);
        System.out.println(fis.markSupported());
        /**
         * markSupported 方法false
         * InputStream中的这个方法，也还是直接设定为false，
         * mark，reset方法也都不可以用
         */



    }
}
