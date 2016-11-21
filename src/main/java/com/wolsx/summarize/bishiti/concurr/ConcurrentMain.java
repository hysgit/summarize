package com.wolsx.summarize.bishiti.concurr;

/**
 * 并发: 以下代码对Counter中的cnt的操作，会不会有线程安全性问题？ 为什么?
 */

class Counter {
    public volatile static long cnt = 0;

    public synchronized void cntAddSome() {
        cnt = cnt + 1;
    }
}

public class ConcurrentMain {
    public static void main(String[] args) throws InterruptedException {
        Counter counter1 = new Counter();
        Counter counter2 = new Counter();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    counter1.cntAddSome();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    counter2.cntAddSome();
                }
            }
        }).start();
        while (true) ;
    }
}
