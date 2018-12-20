package com.seven.Blog.bo;

import com.seven.Blog.util.PropertiesUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created By Seven.wk
 * Description: Redis资源池
 * Created At 2018/11/10
 */
public class RedisPool {
    private static JedisPool jedisPool;

    private static String redisIp = PropertiesUtil.getProperty("redis.ip", "127.0.0.1");
    private static Integer redisPort = Integer.parseInt(PropertiesUtil.getProperty("redis.port", "6379"));
    private static String redisPassword = PropertiesUtil.getProperty("redis.password", "");

    private static Integer maxTotal = Integer.parseInt(PropertiesUtil.getProperty("redis.max.total","20"));
    private static Integer maxIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.max.idle","20"));
    private static Integer minIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.min.idle","20"));

    private static Boolean testOnBorrow = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.borrow","true"));
    private static Boolean testOnReturn = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.return","true"));

    private static void initPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);

        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        jedisPoolConfig.setTestOnReturn(testOnReturn);

        // 连接资源耗尽时，是否会阻塞，true表示会阻塞直到超时，false则会抛出异常
        jedisPoolConfig.setBlockWhenExhausted(true);

        jedisPool = new JedisPool(jedisPoolConfig, redisIp, redisPort, 1000 * 2, redisPassword);
    }

    static {
        initPool();
    }

    public static Jedis getJedis() {
        return jedisPool.getResource();
    }

    public static void returnResource(Jedis jedis) {
        jedis.close();
    }
}
