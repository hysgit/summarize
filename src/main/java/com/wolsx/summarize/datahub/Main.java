package com.wolsx.summarize.datahub;

import com.aliyun.datahub.DatahubClient;
import com.aliyun.datahub.DatahubConfiguration;
import com.aliyun.datahub.auth.Account;
import com.aliyun.datahub.auth.AliyunAccount;
import com.aliyun.datahub.common.data.Field;
import com.aliyun.datahub.common.data.FieldType;
import com.aliyun.datahub.common.data.RecordSchema;
import com.aliyun.datahub.common.data.RecordType;
import com.aliyun.datahub.exception.InvalidCursorException;
import com.aliyun.datahub.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hy on 3/2/17.
 */
public class Main {

    public static void main(String[] args) {
        // 新建client
        Account account = new AliyunAccount("qSpAmAceoCYuyMfi", "rsFam7X2aI45G7TuFLfaPugi1HYZ4y");
        DatahubConfiguration conf = new DatahubConfiguration(account, "http://dh-cn-hangzhou.aliyuncs.com");
        DatahubClient client = new DatahubClient(conf);

//        RecordSchema schema1 = new RecordSchema();
//        schema1.addField(new Field("a", FieldType.STRING));
//        schema1.addField(new Field("b", FieldType.BIGINT));
//        int shardCount = 5;
//        int lifeCycle = 3;
//        String topicName = "bobohsz12";
//        String topicDesc = "topic_example_desc";
//
//        client.createTopic("bobohsz", topicName, shardCount, lifeCycle, RecordType.TUPLE, schema1, topicDesc);
////等待服务端通道打开
//        client.waitForShardReady("bobohsz", topicName);

// 可通过listShard接口获取shard列表，所有ACTIVE的shard均可使用，本利使用"0"
        String shardId = "0";
// 构造需要上传的records
        RecordSchema schema = client.getTopic("bobohsz", "hushizhandatahub").getRecordSchema();
        List<RecordEntry> recordEntries = new ArrayList<>();
        RecordEntry entry = new RecordEntry(schema);
        for (int i=0; i<entry.getFieldCount(); i++) {
            entry.setBigint(i, 1L); //set your fields' value according to the field's type
        }
        entry.setShardId(shardId);
        recordEntries.add(entry);

// 数据写入
        PutRecordsResult result = client.putRecords("bobohsz", "hushizhandatahub", recordEntries);
        if (result.getFailedRecordCount() != 0) {
            List<ErrorEntry> errors = result.getFailedRecordError();
            List<RecordEntry> records = result.getFailedRecords();
            // 存在写入失败的记录，建议日志记录错误原因并重试写入
        }

// 根据时间获取游标
//        GetCursorResult cursorRs = client.getCursor("test_project", "test_topic", shardId, 1455869335000 /*ms*/);

// 数据读取
//每次限读100条，最大不可超过1000
//        int limit = 100;
//        String cursor = cursorRs.getCursor();
//        while (true) {
//            try {
//                GetRecordsResult recordRs = client.getRecords("test_project", "test_topic", shardId, cursorRs.getCursor(), limit, schema);
//                List<RecordEntry> recordEntries = recordRs.getRecords();
//                if (recordEntries.size() == 0) {
//                    // 无最新数据，请稍等重试
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                // 拿到下一个游标
//                cursor = recordRs.getNextCursor();
//            } catch (InvalidCursorException ex) {
//                // 非法游标或游标已过期，建议重新定位后开始消费
//                cursorRs = client.getCursor(projectName, topicName, shardId, GetCursorRequest.CursorType.OLDEST);
//                cursor = cursorRs.getCursor();
//            }
//        }
   }
}
