package cn.ntshare.Blog.util;

import cn.ntshare.Blog.bo.RedisPool;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By Seven.wk
 * Description: RedisPool工具
 * Created At 2018/11/10
 */
@Slf4j
public class RedisUtil {

    /**
     * 设置key的有效期
     * @param key
     * @param expireTime
     * @return
     */
    public static Long expire(String key, int expireTime) {
        Jedis jedis = RedisPool.getJedis();
        Long result = jedis.expire(key, expireTime);
        RedisPool.returnResource(jedis);
        return result;
    }

    /**
     * 设置key的有效期和值
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    public static String setExpireTime(String key, String value, int seconds) {
        Jedis jedis = RedisPool.getJedis();
        String result = jedis.setex(key, seconds, value);
        RedisPool.returnResource(jedis);
        return result;
    }

    /**
     * 设置key的value
     * @param key
     * @param value
     * @return
     */
    public static String set(String key, String value) {
        Jedis jedis = RedisPool.getJedis();
        String result = jedis.set(key, value);
        RedisPool.returnResource(jedis);
        return result;
    }

    /**
     * 根据key值获得value
     * @param key
     * @return
     */
    public static String get(String key) {
        Jedis jedis = RedisPool.getJedis();
        String result = jedis.get(key);
        RedisPool.returnResource(jedis);
        return result;
    }

    /**
     * 删除key
     * @param key
     * @return
     */
    public static Long del(String key) {
        Jedis jedis = RedisPool.getJedis();
        Long result = jedis.del(key);
        RedisPool.returnResource(jedis);
        return result;
    }

    /**
     * 向集合中存放值
     * @param key
     * @param strings
     * @return
     */
    public static Long setList(String key, String... strings) {
        Jedis jedis = RedisPool.getJedis();
        Long result = jedis.lpush(key, strings);
        RedisPool.returnResource(jedis);
        return result;
    }

    /**
     * 获取集合中所有的值
     * @param key
     * @return
     */
    public static List<String> getList(String key) {
        List<String> list = new ArrayList<>();
        Jedis jedis = RedisPool.getJedis();
        while (jedis.exists(key)) {
            list.add(jedis.lpop(key));
        }
        return list;
    }

    public static String set(String key, String value, String nxxx, String expx, long time) {
        Jedis jedis = RedisPool.getJedis();
        String result = jedis.set(key, value, nxxx, expx, time);
        RedisPool.returnResource(jedis);
        return result;
    }

    /**
     * 获取Redis分布式锁
     * @param key
     * @param value
     * @param time      锁的时间，单位（s）
     * @return
     */
    public static boolean getRedisLock(String key, String value, long time) {
        String result = set(key, value, "nx", "ex", time);
        return "OK".equals(result);
    }

    /**
     * 删除Redis分布式锁
     * @param key
     * @param value
     */
    public static void delRedisLock(String key, String value) {
        String result = get(key);
        if (value.equals(result)) {
            RedisUtil.del(key);
        }
    }

    /**
     * 增加定长的值
     * @param key
     * @param value
     * @return
     */
    public static Long incrBy(String key, Long value) {
        Jedis jedis = RedisPool.getJedis();
        Long result = jedis.incrBy(key, value);
        RedisPool.returnResource(jedis);
        return result;
    }
}
