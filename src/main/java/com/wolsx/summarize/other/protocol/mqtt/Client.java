package com.wolsx.summarize.other.protocol.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Client {

    public static final String HOST = "tcp://114.55.48.179:11883";
    public static final String TOPIC = "TIME";
    public static final String TOPIC1 = "TIME/+";
    public static final String TOPIC2 = "PRC/VITAL/007";
    public static final String TOPIC_REV = "TOPICTEST/3";
    public static final String TOPIC_SEND = "TOPICTEST/3";
//    public static final String TOPIC_SEND = "PRC/VITAL/007";
    public static final String TOPIC21 = "HR/001";
    private static final String clientid = "client124331x3";
    private MqttClient client;
    private MqttConnectOptions options;
    private String userName = "bbkj";
    private String passWord = "hzbbkj321";

    private ScheduledExecutorService scheduler;

    public void startReconnect() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Runnable() {
            public void run() {
                //System.out.println("before: "+client.isConnected());
                if (!client.isConnected()) {
                    try {
                        client.connect(options);
                        System.out.println("重连成功: "+client.isConnected());
                        int[] Qos = {1};
                        String[] topic1 = {TOPIC2};
//                        String[] topic1 = {TOPIC_REV};
                        client.subscribe(topic1, Qos);
                    } catch (MqttSecurityException e) {
                        e.printStackTrace();
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, 0 * 1000, 10 * 1000, TimeUnit.MILLISECONDS);
    }


    private void start() {
        try {
            // host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
            client = new MqttClient(HOST, clientid, new MemoryPersistence());
            // MQTT的连接设置
            options = new MqttConnectOptions();
            // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
            options.setCleanSession(true);
            // 设置连接的用户名
            options.setUserName(userName);
            // 设置连接的密码
            options.setPassword(passWord.toCharArray());
            // 设置超时时间 单位为秒
            options.setConnectionTimeout(10);
            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
            options.setKeepAliveInterval(20);
            options.setCleanSession(false);
            // 设置回调
            client.setCallback(new PushCallback(client));
//            MqttTopic topic = client.getTopic(TOPIC);
            //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
//            options.setWill(topic, "close".getBytes(), 2, true);
            System.out.println(client.isConnected());
            client.connect(options);
            startReconnect();
            //订阅消息
            int[] Qos = {1};
            String[] topic1 = {TOPIC2};
//            String[] topic1 = {TOPIC_REV};

            //publishTime();

//            new Thread(() -> {
//                while(true) {
//                    try {
//                        String format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS").format(new Date());
//                        client.publish("TIME/003", format.getBytes(), 1, true);
//                        System.out.println("发布的消息:" + format);
//                        try {
//                            Thread.sleep(1000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    } catch (MqttException e) {
////                        e.printStackTrace();
//                    }
//                }
//            }).start();
            client.subscribe(topic1, Qos);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void publishTime() {
        new Thread(() -> {
            int i = 0;
            long start = new Date().getTime();
            while(i < 1) {
                i++;
                try {
                    String format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS").format(new Date());
                    System.out.println("开始发布消息:"+format);
                    client.publish(TOPIC_SEND, format.getBytes(), 2, true);
                    System.out.println("发布完成:   " + new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS").format(new Date()));
                    if(i%1000 == 0){
                        System.out.println("i = "+ i);
                    }

//                        try {
//                            Thread.sleep(1000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }
            long end = new Date().getTime();
            System.out.println("消耗时间:"+(end-start)+" 平均消耗时间: "+(end-start)/i);
        }).start();
    }

    public static void main(String[] args) throws MqttException, InterruptedException {
        Client client = new Client();
        client.start();

        Thread.sleep(Integer.MAX_VALUE);
    }
}