package com.wolsx.summarize.other.protocol.mqtt;


import org.eclipse.paho.client.mqttv3.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 发布消息的回调类
 * <p>
 * 必须实现MqttCallback的接口并实现对应的相关接口方法CallBack 类将实现 MqttCallBack。
 * 每个客户机标识都需要一个回调实例。在此示例中，构造函数传递客户机标识以另存为实例数据。
 * 在回调中，将它用来标识已经启动了该回调的哪个实例。
 * 必须在回调类中实现三个方法：
 * <p>
 * public void messageArrived(MqttTopic topic, MqttMessage message)接收已经预订的发布。
 * <p>
 * public void connectionLost(Throwable cause)在断开连接时调用。
 * <p>
 * public void deliveryComplete(MqttDeliveryToken token))
 * 接收到已经发布的 QoS 1 或 QoS 2 消息的传递令牌时调用。
 * 由 MqttClient.connect 激活此回调。
 */
public class PushCallback implements MqttCallback {

    private MqttClient client;
    private int cnt = 0;

    public PushCallback(MqttClient client) {
        this.client = client;
    }

    public void connectionLost(Throwable cause) {
        // 连接丢失后，一般在这里面进行重连
        System.out.println("连接断开，可以做重连");
    }

    public void deliveryComplete(IMqttDeliveryToken token) {
        //System.out.println("deliveryComplete---------" + token.isComplete());
    }

    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // subscribe后得到的消息会执行到这里面
        cnt++;
        System.out.println("接收到消息时间:"+new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS").format(new Date()));
        System.out.println("接收消息主题 : " + topic);
        System.out.println("接收消息Qos : " + message.getQos());
//        System.out.println(message.getClass());
//        System.out.println("接收消息内容 : " + new String(message.getPayload()));
        System.out.println("接收消息内容 : " + bytesToHexString(message.getPayload()));
        System.out.println();
        if(cnt >= 9999){
            System.out.println("cnt: "+ cnt);
        }
    }

    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
