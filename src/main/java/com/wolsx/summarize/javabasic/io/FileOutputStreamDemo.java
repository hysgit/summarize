package com.wolsx.summarize.javabasic.io;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by hy
 * Date: 16-4-7.
 */
public class FileOutputStreamDemo {
    public static void main(String[] args) throws IOException {
        /**
         * FileOutputStream: 这个类是字节流输出类，对应到字符流是FileWriter
         *     这个类用给定的文件信息构建字节流对象
         * 继承关系：
         *      java.lang.Object
         *          java.io.OutputStream
                        java.io.FileOutputStream
         *  构造方法：
         *      FileOutputStream(File file)
         *      FileOutputStream(File file, boolean append)
         *      FileOutputStream(FileDescriptor fdObj)
         *      FileOutputStream(String name)
         *      FileOutputStream(String name, boolean append)
         *  方法：
         *      void close()
         *      protected void finalize()
         *      FileChannel getChannel() 属于nio，暂且不论
         *      FileDescriptor getFD()  获取该输出流关联的文件的文件描述符
         *      void write(byte[] b)
         *      void write(byte[] b, int off, int len)
         *      void write(int b)
         *  继承的方法：
         *      void flush()： 输出流都有这个问题,因为写数据到数据流的时候，并不是立刻输出到流关联的文件，而是放在缓存中。
         *                      等文件关闭或者调用flush的时候，才写到文件中。
         *
         *
         */
        String filePath = "/home/hy/workspace/wrksp7/summarize/src/main/java/com/wolsx/summarize/io/txt/gbk.txt";
        FileOutputStream fos = new FileOutputStream(filePath);
        FileDescriptor fd = fos.getFD();
        System.out.println(fd.valid());
        fos.close();
        /**
         * 这个类的一些方法已经很熟悉，不再测试
         */

    }
}
