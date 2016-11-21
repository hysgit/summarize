package com.wolsx.summarize.bishiti;

/**
 * Created by hy on 11/18/16.
 */
public class LambdaTest {
    public static void main(String[] args) {
        new Thread(()->{
            System.out.println("");
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(this.getClass());
            }

    }).start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
