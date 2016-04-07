package com.wolsx.summarize.io;

import java.io.*;

/**
 * Created by hy
 * Date: 16-4-6.
 */
public class InputStreamReaderDemo {
    public static void main(String[] args) throws IOException {

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
         * 因为，从字符流到字节流的读，只需要把文件单做字节流对待即可
         * 而，从字节流到字符流的写，这种转换并不是一定能的，有些字节并不是字符，比如二进制文件，是不能写成字节文件的
         * 不管是从字节流到字符流的读，还是从字符流到字节流的读，都是可控的。
         *
         * InputStreamReader处理的字节流本身对应的内容是字符形式存在的，只不过字符流传输或者存储的时候，必定是以字节流形式
         * FileReader类实际上，本身就具备了把一个文件处理成字符流的能力,因为，FileReader就是因为继承自InputStreamReader
         * 所以才具有这个能力
         */

        /**
         * InputStreamReader的继承结构：
         *      java.lang.Object
                    java.io.Reader
                        java.io.InputStreamReader
         * 加快读的效率：
         *  BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
         *  构造函数：
         *      InputStreamReader(InputStream in)
         *      InputStreamReader(InputStream in, Charset cs)
         *      InputStreamReader(InputStream in, CharsetDecoder dec)
         *      InputStreamReader(InputStream in, String charsetName)
         *      从这四个构造函数可以看出，其核心的参数都是InputStream这个字节流对象，第一个构造函数，使用默认的字符编码
         *      后面3个构造函数，都是附加了流的编码，只是表现形式不同。
         *      这里流的编码，指的是对字节流使用哪种编码进行解码，因为对于字节流本身来说，没有所谓的编码，编码从来都是对于字符来说的
         *  方法：   void close()
         *          String	getEncoding()：该InputStreamReader流所使用的编码
         *          int	read()
         *          int	read(char[] cbuf, int offset, int length)
         *          boolean	ready()
         *          同时，它还有从Reader继承的方法
         *
         */

        /**
         * InputStreamReader(InputStream in)
         * 查看其编码
         */
        //先构建一个InputStream对象
        InputStream fis = new FileInputStream("/home/hy/workspace/wrksp7/summarize/src/main/java/com/wolsx/summarize/io/summarize.txt");
        //创建InputStreamReader对象
        InputStreamReader isr = new InputStreamReader(fis);
        System.out.println("isr.ready(): "+isr.ready());
        System.out.println("isr.getEncoding(): "+isr.getEncoding());        //Java默认情况下，字符是UTF-8编码

        fis.close();
        //在JDK7中，这个类实现了AutoCloseable接口，这个接口中的close方法，
        // 会在try-with-resources block中被调用，这个以后讨论
        isr.close();

        /**
         * InputStreamReader(InputStream in, String charsetName)
         * 这个构造函数，用字符串指定字符流的编码
         */
        fis = new FileInputStream("/home/hy/workspace/wrksp7/summarize/src/main/java/com/wolsx/summarize/io/summarize.txt");
        isr = new InputStreamReader(fis,"UTF-8");        //UTF8编码的系统表示方法是：UTF8，而写成UTF-8或者utf-8均能被识别为UTF8
        System.out.println(isr.getEncoding());
        fis.close();
        isr.close();

        /**
         * 如果一个文件是utf-8编码的，以gbk解码会怎么样？
         */
        fis = new FileInputStream("/home/hy/workspace/wrksp7/summarize/src/main/java/com/wolsx/summarize/io/summarize.txt");
        isr = new InputStreamReader(fis,"GBK");        //UTF8编码的系统表示方法是：UTF8，而写成UTF-8或者utf-8均能被识别为UTF8
        int ch = -1;
        while((ch = isr.read()) != -1)
        {
            System.out.print((char)ch);     //可以看到，汉字输出的时候，就乱码了，而英文字符不会乱码，因为GBK编码兼容了ASCII
        }
        System.out.println();

        /**
         * InputStreamReader(InputStream in, Charset cs)
         *  Charset是nio的东西
         *
         */

        /**
         * InputStreamReader(InputStream in, CharsetDecoder dec)
         * 这部分也是nio的东西
         */






    }
}
