package com.wolsx.summarize.other.redis.cas;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hy on 10/26/16.
 */
public class RedisGetTime {
    private static String redisHost = "192.168.1.220";
//    private static String redisHost = "127.0.0.1";
    private static String access_token_key = "global_weixin_access_token_key";
    private static String flag_key = "global_weixin_flag_key";
    private static String incr_key = "global_weixin_incr_key";

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    private static int port = 6379;

    private static JedisPool jedisPool;
    private static int threads = 100;

    private static CountDownLatch latch;

    public static void main(String[] args) {
        jedisPool = createRedisPool();
        Jedis con = jedisPool.getResource();
        List<String> time = con.time();
        System.out.println(time);
        System.out.println(new Date().getTime());
    }





    private static JedisPool createRedisPool() {

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(10);
        config.setMaxWaitMillis(300000);
        config.setMaxTotal(30000);
        return new JedisPool(config, redisHost, port, 300000);
    }
}


