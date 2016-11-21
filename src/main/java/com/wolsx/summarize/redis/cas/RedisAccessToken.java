package com.wolsx.summarize.redis.cas;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hy on 10/26/16.
 */
public class RedisAccessToken {
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
        new Thread(new ClearToken()).start();       //取消token

        for (int i = 0; i < threads; i++) {
            new Thread(new GetToken()).start();
        }
        latch = new CountDownLatch(threads);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class GetToken implements Runnable {

        @Override
        public void run() {
            while (true) {
                getAccessToken();
            }
        }
    }

    static class ClearToken implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    for (int i = 0; i < 4; i++) {
                        Thread.sleep(1000);
                        System.out.println(i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Jedis conn = null;
                try {
                    conn = jedisPool.getResource();
                    conn.del(access_token_key);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (conn != null) {
                        jedisPool.returnResourceObject(conn);
                    }
                }

            }
        }
    }

    public static String getAccessToken() {
        Jedis conn = null;
        String accessToken = null;
        Date date = new Date();
        try {
            conn = jedisPool.getResource();

            accessToken = conn.get(access_token_key);
            if (accessToken != null) {
                return accessToken;
            }
            List<String> time = conn.time();
            if(time == null)
            {
                return null;
            }
            String sec = time.get(0);
            Long now = Long.valueOf(sec);
            Long setnx = conn.setnx(flag_key, now+"");
            if(!(setnx.longValue() == (long)1))
            {   //没有获取到锁的时候，判断锁是否过期
                String oldTime = conn.get(flag_key);
                if(oldTime == null)//没有这个key,说明此时锁过期了或者被释放了，则直接返回，可能值已经被获取到了
                {
                    return null;
                }
                if(now - Long.valueOf(oldTime) < 60)
                {
                    //锁失效且时间未到
                    return null;
                }
            }
            accessToken = conn.get(access_token_key);        //判断是否其他线程获取了新的token
            if (accessToken != null) {
                return accessToken;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss:SSSS");
            System.out.println(sdf.format(new Date()) + "当前线程： " + Thread.currentThread() + "获取到了所有权,开始获取新的token ");

            accessToken = UUID.randomUUID().toString();
            conn.set(access_token_key, accessToken);
            conn.expire(access_token_key, 90 * 60);
            conn.del(flag_key);
            System.out.println(sdf.format(new Date()) + "当前线程： " + Thread.currentThread() + "设置了新的token 方法起始事件：" + sdf.format(date));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                jedisPool.returnResourceObject(conn);
            }
        }
        return accessToken;
    }

    public static JedisPool createRedisPool() {

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(10);
        config.setMaxWaitMillis(300000);
        config.setMaxTotal(30000);
        return new JedisPool(config, redisHost, port, 300000);
    }
}


