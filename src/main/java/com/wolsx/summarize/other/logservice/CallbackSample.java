package com.wolsx.summarize.other.logservice;

import com.aliyun.openservices.log.common.LogItem;
import com.aliyun.openservices.log.exception.LogException;
import com.aliyun.openservices.log.producer.ILogCallback;
import com.aliyun.openservices.log.producer.LogProducer;
import com.aliyun.openservices.log.response.PutLogsResponse;

import java.util.Vector;

/**
 * Created by hy on 3/1/17.
 */
public class CallbackSample extends ILogCallback{
    //保存要发送的数据，当时发生异常时，进行重试
    public String project;
    public String logstore;
    public String topic;
    public String shardHash;
    public String source;
    public Vector<LogItem> items;
    public LogProducer producer;
    public int retryTimes = 0;
    public CallbackSample(String project, String logstore, String topic,
                          String shardHash, String source, Vector<LogItem> items, LogProducer producer) {
        super();
        this.project = project;
        this.logstore = logstore;
        this.topic = topic;
        this.shardHash = shardHash;
        this.source = source;
        this.items = items;
        this.producer = producer;
    }
    public void onCompletion(PutLogsResponse response, LogException e) {
        if (e != null) {
            // 打印异常
            System.out.println(e.GetErrorCode() + ", " + e.GetErrorMessage() + ", " + e.GetRequestId());
            //最多重试三次
            if(retryTimes++ < 3)
            {
                producer.send(project, logstore, topic, source, shardHash, items, this);
            }
        }
        else{
            System.out.println("send success, request id: " + response.GetRequestId());
        }
    }
}
