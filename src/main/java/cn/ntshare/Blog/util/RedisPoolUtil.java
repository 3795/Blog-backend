package cn.ntshare.Blog.util;

import cn.ntshare.Blog.bo.RedisPool;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

/**
 * Created By Seven.wk
 * Description: RedisPool工具
 * Created At 2018/11/10
 */
@Slf4j
public class RedisPoolUtil {

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

}
