package com.wolsx.summarize.io;

import java.io.*;

/**
 * Created by hy
 * Date: 16-4-7.
 */
public class BufferedInputStreamDemo {
    public static void main(String[] args) throws IOException {
        /**
         * BufferedInputStream: 字节输入流缓冲类，也就是为字节输入流提供了缓冲区功能,提高了读字节输入流的效率
         * 继承：
         *      java.lang.Object
                    java.io.InputStream
                        java.io.FilterInputStream
                            java.io.BufferedInputStream
         *  这里可以看出，这个类是继承自FilterInputStream，也就是说它是个包装类，看构造函数就能知道，
         *  它就是对字节输入流进行了包装
         *  因为这个类是继承自InputStream类，又因为它没有新增加公共方法，只是覆盖了InputStream的方法，
         *  所以把它当做InputStream对象使用，效果是一样的。只不过它支持了缓冲和mark/reset，但是使用上是一样的。
         *
         *
         * 构造函数：
         *      BufferedInputStream(InputStream in) 用InputStream对象构建一个带缓冲功能的输入流
         *                  这个时候，构造函数内部实际是用一个默认的缓冲区大小，调用下面的构造函数。默认缓冲区大小是8192字节
         *      BufferedInputStream(InputStream in, int size) 功能同上，但是它能设置缓冲区大小
         * 它有以下几个域，是和标记缓冲区有关的，用这个功能，可以实现字节输入流原本不支持的重复读功能
         *      protected byte[]	buf     缓冲区 第一次读取的时候，就会从输入流中读满缓冲区
         *      protected int	count       这个实际上是一个下标，指向缓冲区中最后一个有效字节位置 后的一个位置
         *                                  因为数组的下表是从0开始的，这个字段的实际意思是：数组中，有多少个单元的内容是有效的
         *                                  也就是说，缓冲流上一次从输入流中读取数据的时候，读取到了多少个字节,它的初始值是0
         *      protected int	marklimit   在调用mark方法时，传进去的参数，将会设置给这个变量
         *      protected int	markpos     标记位置 最后一次调用mark时pos的值，也就是mark标记的位置
         *      protected int	pos         当前位置
         *      这几个字段，都是BufferedInputStream内部使用的，当用它创建对象后，不能通过对象直接访问它
         *
         * 方法：
         *      int	available()
         *      void	close()
         *      void	mark(int readlimit)
         *      boolean	markSupported()
         *      int	read()
         *      int	read(byte[] b, int off, int len)
         *      void	reset()
         *      long	skip(long n)
         *
         */

        String filePath = "/home/hy/workspace/wrksp7/summarize/src/main/java/com/wolsx/summarize/io/txt/summarize.txt";
        InputStream is = new FileInputStream(filePath);
        BufferedInputStream bis = new BufferedInputStream(is);
        while (bis.read() != -1) ;
        bis.close();
        is.close();

        /**
         * 如果某次读取的时候，缓冲区中剩余的字节不够读，那么会再次去读取缓冲区大小的字节数，并把后续需要读取的字节数，传给数组
         */
        is = new FileInputStream(filePath);
        bis = new BufferedInputStream(is,10);
        byte[] buff = new byte[6];
        while (bis.read(buff) != -1) {

        }
        bis.close();
        is.close();


        /**
         * 调用mark方法的时候，会把参数设置给marklimit
         * 并且把当前位置pos设置给markpos
         * 当调用reset的时候，就是把markpos又设置给pos
         * 通俗点来说，就是在当前位置做个标记，然后reset一下，又回到这个点
         *
         */
        is = new FileInputStream(filePath);
        bis = new BufferedInputStream(is,10);
        boolean b = true;
        int i = 0;
        bis.read();
        bis.read();
        bis.mark(15);
        while (b) {
            int x = bis.read();
            if (x == -1) {
                break;
            }
            else{
                System.out.println(x);
            }
            i++;
            if(i==8)
                break;
        }
        bis.reset();    //运行reset之前：pos = 10, markpos = 2 运行之后：pos=2
        System.out.println(bis.read());

        bis.close();
        is.close();

        /**
         * marklimit的作用，以及整个mark、reset体系的工作原理
         * 当用mark记录缓冲区中的某个点的时候，会传进去一个值传给marklimit，这个值，就是最大可回退的字节数
         * 也就是说，当在缓冲区中做标记后，从标记位置开始，以后每个字节，都不能因为已经读取或者被跳过而丢弃，
         * 也就是说这些位置的数据是要保留原样的，但是，不能无限地保留，也就是说要让mark标记的位置的有效性是有限的
         * 而这个有效性是根据有效字节的个数来定的，这个有效字节数，就是marklimit值。
         * 在mark标记的位置和当前位置超过marklimit时，markpos被置为-1，也就是说此前mark的标记已经无效，此时调用reset，会抛异常
         * 一般来说缓冲区的长度在缓冲流建立后就固定了，但是，如果marklimit大于当前缓冲区长度，那么，缓冲区的长度会进行扩充
         * 如果当前标记的位置到缓冲区末尾的长度小于marklimit，但marklimit又小于缓冲区长度，那么当读取到缓冲区末尾的时候，再读一次，
         * 则会把pos位置前的无效字节去除，把剩余的字节前移，然后从输入流读取字节补充后面的位置，同时，把markpos置为0
         */
        is = new FileInputStream(filePath);
        bis = new BufferedInputStream(is,10);
        b = true;
        i = 0;
        bis.read();
        bis.read();
        bis.mark(9);
        while (b) {
            int x = bis.read();
            if (x == -1) {
                break;
            }
            i++;
            if(i>100)
                break;
        }
        bis.reset();
        bis.close();
        is.close();

    }
}
