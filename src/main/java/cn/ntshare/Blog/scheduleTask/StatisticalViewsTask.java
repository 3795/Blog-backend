package cn.ntshare.Blog.scheduleTask;

import cn.ntshare.Blog.pojo.Message;
import cn.ntshare.Blog.pojo.Statistics;
import cn.ntshare.Blog.service.RabbitMqService;
import cn.ntshare.Blog.service.StatisticsService;
import cn.ntshare.Blog.util.CalendarUtil;
import cn.ntshare.Blog.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

import static cn.ntshare.Blog.constant.SystemConstant.redisLockKey;
import static cn.ntshare.Blog.constant.SystemConstant.redisLockTime;

/**
 * Created By Seven.wk
 * Description: 统计访问量任务
 * Created At 2019/01/16
 */
@Component
@Slf4j
public class StatisticalViewsTask {

    @Autowired
    private StatisticsService statisticsService;

    @Value("${server.port}")
    public String redisLockValue;

    @Autowired
    private RabbitMqService rabbitMqService;

    /**
     * 添加明天的访问量记录
     * 每天23点58分执行
     */
    @Scheduled(cron = "0 58 23 * * *")
    public void insertDailyViews() {
        if (!RedisUtil.getRedisLock(redisLockKey, redisLockValue, redisLockTime)) {
            return;
        }
        Date tomorrow = CalendarUtil.getTomorrowDate();
        Statistics statistics = new Statistics(0, tomorrow);
        if (statisticsService.insertDailyStatistics(statistics)) {
            log.info("明日访问量记录添加成功");
        } else {
            log.error("明日访问量记录添加失败");
        }
        rabbitMqService.sendNotice(new Message("分布式锁", "Server " + redisLockValue + " 获得分布式锁并写入记录"));
        RedisUtil.delRedisLock(redisLockKey, redisLockValue);
    }

    /**
     * 添加下一个月的记录
     * 每月第一天0分0秒执行
     */
    @Scheduled(cron = "0 0 0 1 * *")
    public void insertMonthlyViews() {
        if (!RedisUtil.getRedisLock(redisLockKey, redisLockValue, redisLockTime)) {
            return;
        }
        String month = CalendarUtil.getCurrentMonth();
        Statistics statistics = new Statistics(0, month);
        if (statisticsService.insertMonthlyStatistics(statistics)) {
            rabbitMqService.sendNotice(new Message("添加访问量记录", "下一月访问量记录添加成功, 月份为 " + month));
            log.info("下一月访问量记录添加成功");
        } else {
            log.error("下一月访问量记录添加失败");
        }
        RedisUtil.delRedisLock(redisLockKey, redisLockValue);
    }

    /**
     * 将昨天的访问量加入月访问量中
     * 每天0点03分执行
     * 每个月最后一天的记录在下一个月的记录里
     */
    @Scheduled(cron = "0 3 0 * * *")
    public void increaseMonthlyViews() {
        if (!RedisUtil.getRedisLock(redisLockKey, redisLockValue, redisLockTime)) {
            return;
        }
        Integer views = statisticsService.queryYesterdayViews().getViews();
        statisticsService.increaseMonthlyViews(views);
        rabbitMqService.sendNotice(new Message("访问量统计", "昨日访问量为 " + views));
        RedisUtil.delRedisLock(redisLockKey, redisLockValue);
    }
}
