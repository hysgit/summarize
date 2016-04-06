package com.wolsx.summarize.io;

/**
 * Created by hy
 * Date: 16-4-6.
 */
public class InputStreamReaderDemo {
    public static void main(String[] args) {

        /**
         * InputStreamReader是从字节流到字符流的桥梁
         * 它能读取字节流数据，然后根据设定的或者默认的字符编码，
         * 把字节流解码为字符
         * 因为一个字符可能需要一个或者多个字节，所以，当从流中读取一个字符的时候，
         * 会引起底层读取一个或者多个字节。
         *
         * 所以，这个类的作用就是，用一个字节流构建一个字符流对象，然后从中读取字符。
         * 所以这个类就是从字节流到字符流的桥梁
         *
         * 同样的还有一个类是从字符流到字节流的，是一个进行写操作的类
         * 为什么从字节流到字符流是读，而从字符流到字节流是写？
         * 为什么没有从字节流到字符流的写，以及从字符流到字节流的读？
         */
    }
}
