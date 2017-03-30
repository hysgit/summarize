package com.wolsx.summarize.other.logservice;

import com.aliyun.openservices.log.common.LogItem;
import com.aliyun.openservices.log.producer.LogProducer;
import com.aliyun.openservices.log.producer.ProducerConfig;
import com.aliyun.openservices.log.producer.ProjectConfig;

import java.util.Date;
import java.util.Random;
import java.util.Vector;

/**
 * Created by hy on 3/1/17.
 */
public class ProducerSample {
    private final static int ThreadsCount = 1;
    public static String RandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int num = random.nextInt(62);
            buf.append(str.charAt(num));
        }
        return buf.toString();
    }
    public static void main(String args[]) throws InterruptedException {
        ProducerConfig producerConfig = new ProducerConfig();
        //使用默认配置创建 producer 实例
        final LogProducer producer = new LogProducer(producerConfig);
        // 添加多个 project 配置
        producer.setProjectConfig(new ProjectConfig("hushizhan",
                "cn-hangzhou.log.aliyuncs.com", "qSpAmAceoCYuyMfi", "rsFam7X2aI45G7TuFLfaPugi1HYZ4y"));
        System.out.println("创建producer");
        // 生成日志集合，用于测试
        final Vector<Vector<LogItem>> logGroups = new Vector<Vector<LogItem>>();
        // "BEDS:{\"bedNo\":\"1\",\"heart_rate\":0,\"index\":9990,\"leave_Bed\":true,\"leave_bed_time\":22516,\"resp_rate\":0,\"sn\":\"1\",\"timestamp\":1488424859000}";
        String s = "2,%s,%s,false,0,%d,2,%s";

        Integer index = 0;
        boolean incr = true;
        boolean rspincr = true;
        Integer heart = 60;
        Integer rsp = 10;
        for (int i = 0; i < 100000; ++i) {

            if(heart < 50)
            {
                incr  = true;
            }
            if(heart > 70)
            {
                incr = false;
            }
            if(incr){
                heart++;
            }
            else{
                heart--;
            }

            if(rsp < 10)
            {
                rspincr  = true;
            }
            if(rsp > 30)
            {
                rspincr = false;
            }
            if(rspincr){
                rsp++;
            }
            else{
                rsp--;
            }

            Vector<LogItem> tmpLogGroup = new Vector<LogItem>();
            LogItem logItem = new LogItem((int) (new Date().getTime() / 1000));
//            logItem.PushBack("level", "debug" + System.currentTimeMillis());
            logItem.PushBack("bedNo", "2");
            logItem.PushBack("heart_rate", "" +heart);
            logItem.PushBack("index", "" +index);
            logItem.PushBack("leave_Bed", "false" );
            logItem.PushBack("leave_bed_time", "0" );
            logItem.PushBack("resp_rate", "" + rsp);
            logItem.PushBack("sn", "2" );
            logItem.PushBack("timestamp", "" + new Date().getTime());
//            logItem.PushBack("message", String.format(s,heart,index++,rsp,new Date().getTime()));

//                    + RandomString(50));
            //logItem.PushBack("method", "SenderToServer " + RandomString(10));
            tmpLogGroup.add(logItem);
            logGroups.add(tmpLogGroup);
        }
        // 并发调用 send 发送日志
        System.out.println("开始发送");
        Thread[] threads = new Thread[ThreadsCount];
        for (int i = 0; i < ThreadsCount; ++i) {
            threads[i] = new Thread(null, new Runnable() {
                Random random = new Random();
                public void run() {
                    int j = 0, rand = random.nextInt(99999);
                    while (++j < Integer.MAX_VALUE) {
                        System.out.println(logGroups.get(rand).toString());
                        producer.send("hushizhan", "nurse_data", "topic",
                                "192.168.99.106", logGroups.get(rand),
                                null);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, i + "");
            threads[i].start();
        }
        //等待发送线程退出
        Thread.sleep(1 * 60 * 60 * 1000);
        //主动刷新缓存起来的还没有被发送的日志
        producer.flush();
        //关闭后台 io 线程，close 会将调用时刻内存中缓存的数据发送出去
        producer.close();
    }
}
