package com.wolsx.summarize.io;

import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by hy
 * Date: 16-4-7.
 */
public class OutputStreamWriterDemo {

    public static void main(String[] args) throws IOException {
        /**
         * FileWriter类就是继承自OutputStreamWriter
         * 这个类的继承关系：
         *      java.lang.Object
                    java.io.Writer
                        java.io.OutputStreamWriter
         *  这个类是从字符流到字节流的桥梁，简单来说，就是把字符按照默认或者指定的编码，encode为字节并输出
         *  InputStreamReader是为了解决从文本文件或者其他输入流中直接读取字符的问题，因为文件是以字节的方式存储的，
         *  所以要想读取到字符，必然有个解码的过程，如果没有这个类，那么读取字符的时候，要自己把读取到的字节解码为字符
         *  同样，OutputStreamWriter，是为了解决写字符的问题，因为字符输出到文件或者其他，必然是以二进制的形式保存的，所以也有一个对
         *  字符进行编码的问题，这个类也是提供了一个便捷的形式。
         *  虽然Reader类和Writer类是字符处理的顶层类，并且，他们提供了字符的读写方法，但是，他们都是抽象类，是不能直接使用的类。
         *  而InputStreamReader和OutputStreamWriter是他们的子类，是可以实例化的类。在字符输入输出上，这2个类是最基本的类，
         *  最通用。
         *  他们处理的对象，是任何的字节输入流和字节输出流
         *  所以，字符流，是对字节流的解码包装。字符流是字节流的更高一层。
         *  而FileReader和FileWriter是InputStreamReader和OutputStreamWriter的子类，他们是特别为了处理文本文件而实现的类。
         *  有了这2个类，操作文件的时候，就不用先建立字节输入流和字节输出流了。
         *  因为这里面有个编码转换的工作，所以每次调用write方法的时候，都会在要写的字符上调用转换器，对字符进行转换
         *  虽然传递给write方法的字符没有缓冲，但是，字符转换为字节后，是存在缓冲区中的，而不是马上写到输出流。
         *
         * 构造函数：
         *      OutputStreamWriter(OutputStream out)
         *      OutputStreamWriter(OutputStream out, Charset cs)
         *      OutputStreamWriter(OutputStream out, CharsetEncoder enc)
         *      OutputStreamWriter(OutputStream out, String charsetName)
         *      从构造函数，可以看出，这个类就是把字符，按照默认的或者给定的编码进行encode，然后把结果输出到给定的字节输出流
         * 方法：
         *      getEncoding():获取到该字符流所使用的编码，也就是说，按照哪中编码进行encode
         *
         */

        /**
         * 输出字符到标准输出
         * 标准输出拿到的数据也是二进制的，然后它会根据自己当前使用的编码方式进行解码，还原为字符
         * 也就是说，在计算机上，一切实际上都是以二进制的形式流动的，
         * 字符，只是一种图形，不同的字符，在特定的编码字符集下对应不同的二进制编码
         * 对一个字符使用特定的一种字符编码进行encode，那么decode的时候，必须也要是同一种编码字符集
         */
        OutputStreamWriter osw = new OutputStreamWriter(System.out,"UTF-8");
        osw.write("标准输出");
        System.out.println(osw.getEncoding());
        osw.close();//切记，字符输出流，必须要flush或者close，才会输出到目标

        /**
         * 这个类的其他功能，在FileWriter中测试过了
         * 其他疏忽的东西以后再补充
         */



    }
}
