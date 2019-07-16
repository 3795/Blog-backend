package cn.ntshare.Blog.scheduleTask;

import cn.ntshare.Blog.constant.SystemConstant;
import cn.ntshare.Blog.util.RedisUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static cn.ntshare.Blog.constant.SystemConstant.REDIS_LOCK_KEY;
import static cn.ntshare.Blog.constant.SystemConstant.REDIS_LOCK_TIME;

/**
 * 定时增加网站的运行时间
 */
@Component
public class RuntimeTimeTask {

    @Value("${server.port}")
    public String redisLockValue;

    // 增加运行天数，每天凌晨执行
    @Scheduled(cron = "0 0 0 * * *")
    public void increaseDay() {
        if (!RedisUtil.getRedisLock(REDIS_LOCK_KEY, redisLockValue, REDIS_LOCK_TIME)) {
            return;
        }
        String days = RedisUtil.get(SystemConstant.RUNTIME_DAYS);
        if (null == days || "".equals(days)) {
            RedisUtil.set(SystemConstant.RUNTIME_DAYS, String.valueOf(336));
        } else {
            RedisUtil.incrBy(SystemConstant.RUNTIME_DAYS, 1L);
        }
        RedisUtil.set(SystemConstant.RUNTIME_SECONDS, "0");
        RedisUtil.delRedisLock(REDIS_LOCK_KEY, redisLockValue);
    }

    // 增加运行秒数，每30s增加一次
    @Scheduled(cron = "0/30 * * * * *")
    public void increaseSecond() {
        if (!RedisUtil.getRedisLock(REDIS_LOCK_KEY, redisLockValue, REDIS_LOCK_TIME)) {
            return;
        }
        RedisUtil.incrBy(SystemConstant.RUNTIME_SECONDS, 30L);
        RedisUtil.delRedisLock(REDIS_LOCK_KEY, redisLockValue);
    }
}
