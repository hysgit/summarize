package com.wolsx.summarize.javabasic.io;

/**
 * Created by hy
 * Date: 16-4-6.
 */

import java.io.*;

/**
 * 这个类演示通过FileReader类读取文本文件信息
 *
 * 总的继承结构：
 * java.lang.Object
    java.io.Reader
        java.io.InputStreamReader
            java.io.FileReader
 它实现的接口：
    Closeable, AutoCloseable, Readable
 从继承关系来看，这个流是继承自Reader，这是个字符流
 这个类没有任何自定义方法，所有方法都是继承来的,这个类继承于InputStreamReader，它只是特定于从文件构建字符输入流对象
 所以，它的构造函数，都是方便从文件角度创建对象的：
 FileReader(String fileName) 根据文件名创建字符输入流对象
 FileReader(File file)  根据文件对象创建字符输入流对象
 FileReader(FileDescriptor fd)  根据文件描述符创建字符输入流对象

 Reader的 read()方法 有4个，而InputStreamReader继承了Reader，但是，InputStreamReader实现了int read()，重新定义了
 read(char cbuf[], int offset, int length)方法
 根据继承规则，当FileReader类调用这2个read方法的时候，调用的是InputStreamReader方法。
 */
public class FileReaderDemo {
    public static void main(String[] args) throws IOException {
        /**
         * FileReader(String fileName)构造函数
         * 参数是文件名的路径，这里演示的是绝对路径
         * Creates a new FileReader, given the name of the file to read from.
         */
        System.out.println("-------------int read()----------------");
        FileReader fr = new FileReader("/home/hy/workspace/wrksp7/summarize/src/main/java/com/wolsx/summarize/io/txt/summarize.txt");

        //然后用read()方法读取单个字节，这里用的是InputStreamReader的方法，因为InputStreamReader从新定义了read()方法，覆盖了Reader中的方法
        int ch;
        while((ch = fr.read())!= -1)
        {
            System.out.print((char)ch);
        }
        System.out.println();
        //关闭文件
        fr.close();


        /**使用read(char cbuf[])读取,这个方法是从Reader继承的方法
         * 这个方法内部，实际上是调用read(cbuf, 0, cbuf.length)方法,但是，这个方法在Reader类中是抽象方法，
         * 所以实际是调用的InputStreamReader的read(cbuf, 0, cbuf.length)方法，
         * ###也就是说，在抽象类中，可以调用其自身的抽象方法###，因为在这个抽象类被实现为具体类的时候，它的抽象方法必然被实现
         * 这个方法是把输入流中的字符，读取到cbuf数组中。
         */
        System.out.println("---------int read(char cbuf[])-------------");
        fr = new FileReader("/home/hy/workspace/wrksp7/summarize/src/main/java/com/wolsx/summarize/io/txt/summarize.txt");
        char[] buff = new char[1024];

        while(fr.read(buff)!= -1)
        {
            System.out.print(buff);
        }
        System.out.println();
        fr.close();



        /**
        read(char cbuf[], int off, int len) FileReader使用的这个方法，继承自InputStreamReader
        这个方法从流中读取数据，存储到指定的数组的指定起始位置开始的空间，而原来的数据不受影响
        */
        System.out.println("-----------int read(char cbuf[], int offset, int length)-------------");
        fr = new FileReader("/home/hy/workspace/wrksp7/summarize/src/main/java/com/wolsx/summarize/io/txt/summarize.txt");
        buff = new char[50];
        buff[0] = '0';
        buff[1] = '1';
        while (fr.read(buff, 2, buff.length-2) != -1) {
            System.out.print(buff);
        }
        System.out.println();
        fr.close();


        /**
         * read(java.nio.CharBuffer target)方法
         * 这个方法是把字符流的数据读取到CharBuffer对象中
         * 这个方法是nio范围的，暂且不讨论
         *
         */

        /**
         * long skip(long n)
         * 这个方法是在读取字符的时候，跳过n个字符，返回值是成功跳过的字符个数。
         * 如果n为负数，则抛出IllegalArgumentException异常
         */
        System.out.println("--------------long skip(long n)------------");
        fr = new FileReader("/home/hy/workspace/wrksp7/summarize/src/main/java/com/wolsx/summarize/io/txt/summarize.txt");
        while ((ch = fr.read()) != -1) {
            System.out.println(fr.skip(3));
            System.out.println((char)ch);
        }
        fr.close();
        System.out.println();

        /**
         * boolean ready(): 判断流是否已经准备好可以读取
         * 这个主要用来判断，流是否可以进行下次读取，如果还不可以进行下次读取，那么直接读取的话，会导致程序阻塞在读取这里
         * boolean markSupported():测试流是否支持mark，Reader类中默认实现总是false。因为FileReader和InputStreamReader均未
         * 重新定义这个方法，所以，实际上，在FileReader类中，这个方法并没有用
         *而mark功能，默认实现：
         *      public void mark(int readAheadLimit) throws IOException {
                    throw new IOException("mark() not supported");
                }
         * 所以，mark功能也不可以用，并且，reset也不可以用，使用都会抛出异常
         */
        System.out.println("----------boolean ready()-- --------");
        System.out.println("----------void reset()-- --------");
        System.out.println("----------void mark(int readAheadLimit)-------");
        System.out.println("----------boolean markSupported()-------");
        fr = new FileReader("/home/hy/workspace/wrksp7/summarize/src/main/java/com/wolsx/summarize/io/txt/summarize.txt");

        System.out.println(fr.ready());     //true
        while ((ch = fr.read()) != -1);
        /**
         * 一个流中的数据读取完之后，这个时候，调用这个方法，就会返回false，这也说明，流是一个连续的，每次读取都和上次相关的
         * 一个流读完后，不能继续读。
         * 以下功能，FileReader类并不支持
         * 如果要再次读取此流，则需要调用void reset()方法，但是，调用该方法前，一定要给流打标记，然后reset到mark的位置，否则会
         * 抛出异常：java.io.IOException: reset() not supported
         * 在进行mark前，需要调用boolean markSupported()，判断以下，此流是否支持标记。
         *
         *
         */
        System.out.println(fr.ready());     //false
        fr = new FileReader("/home/hy/workspace/wrksp7/summarize/src/main/java/com/wolsx/summarize/io/txt/summarize.txt");
        boolean b = fr.markSupported();

        System.out.println(b);
        fr.close();
        System.out.println();

        /**
         * String getEncoding(): 返回该流所使用的编码
         */
        System.out.println("-------------String getEncoding()------------");
        fr = new FileReader("/home/hy/workspace/wrksp7/summarize/src/main/java/com/wolsx/summarize/io/txt/summarize.txt");
        System.out.println(fr.getEncoding());
        fr.close();


        /**
         * 构造函数：FileReader(File file)
         * 参数是一个File对象
         * 使用file对象的好处是，可以用File的exists()方法判断文件是否存在
         */
        File file = new File("/home/hy/workspace/wrksp7/summarize/src/main/java/com/wolsx/summarize/io/txt/summarize.txt");
        System.out.println("文件是否存在："+file.exists());
        fr = new FileReader(file);
        System.out.println(fr.ready());
        fr.close();

        /**
         * FileReader(FileDescriptor fd)
         *  java.io.FileDescriptor:文件描述符类
         *  可以被用来表示开放文件、开放套接字等。
            以FileDescriptor表示文件来说：当FileDescriptor表示某文件时，我们可以通俗的将FileDescriptor看成是该文件。
            但是，我们不能直接通过FileDescriptor对该文件进行操作；若需要通过FileDescriptor对该文件进行操作，
            则需要新创建FileDescriptor对应的FileOutputStream，再对文件进行操作。
            使用方法：
                1. 先构建一个文件输入流，然后获取到这个文件输入流的文件描述符对象
                    然后用这个文件描述符对象构建FileReader对象

         */
        FileInputStream fis = new FileInputStream("/home/hy/workspace/wrksp7/summarize/src/main/java/com/wolsx/summarize/io/txt/summarize.txt");
        FileDescriptor fd = fis.getFD();        //获取到字节流的文件描述符
        fr = new FileReader(fd);
        ch = fr.read();
        System.out.println((char)ch);
        fr.close();

        //利用FileDescriptor中的静态域，构建标准输入流的FileReader对象,然后从a控制台读取字符
        fd = FileDescriptor.in;
        fr = new FileReader(fd);
        ch = fr.read();     //从控制台读取一个字符
        System.out.println((char)ch);
        //System.out.println();

        /**
         *  FileReader和FileWriter类都是使用默认的字符编码，也就是UTF-8
         *  假如读取一个GBK编码的文件，会怎么样？
         *  经过测试，会乱码
         */
        fr = new FileReader("/home/hy/workspace/wrksp7/summarize/src/main/java/com/wolsx/summarize/io/txt/gbk.txt");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(System.out);
        System.out.println(fr.getEncoding());

        while ((ch = fr.read()) != -1) {
            System.out.print((char)ch);     //乱码 会立刻输出
            outputStreamWriter.write(ch);       //乱码 这里不会立刻输出，而是等到后面close的时候才会输出
        }
        fr.close();
        outputStreamWriter.close();

    }
}
