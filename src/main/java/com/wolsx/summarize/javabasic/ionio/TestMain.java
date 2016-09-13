package com.wolsx.summarize.javabasic.ionio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by hy on 7/14/16.
 */
public class TestMain {

    public static void main(String[] args) throws IOException {

        TestMain testMain = new TestMain();
        String path = "/home/hy/ebook/src/Java编程思想第四版源码/polymorphism/Transmogrify.java";
        testMain.ioRead(path);
        testMain.nioRead(path);

    }

    /**
     * 使用IO读取指定文件的前1024个字节的内容。
     * @param file 指定文件名称。
     * @throws java.io.IOException IO异常。
     */
    public void ioRead(String file) throws IOException {
        FileInputStream in = new FileInputStream(file);
        byte[] b = new byte[1024];
        in.read(b);
        System.out.println(new String(b));
    }

    /**
     * 使用NIO读取指定文件的前1024个字节的内容。
     * @param file 指定文件名称。
     * @throws java.io.IOException IO异常。
     */
    public void nioRead(String file) throws IOException {
        FileInputStream in = new FileInputStream(file);
        FileChannel channel = in.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);
        byte[] b = buffer.array();
        System.out.println(new String(b));
    }
}
