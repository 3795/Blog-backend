package cn.ntshare.blog.service;

import cn.ntshare.blog.pojo.Statistics;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;

/**
 * Created By Seven.wk
 * Description: 系统统计服务
 * Created At 2019/01/16
 */
public interface StatisticsService {

    /**
     * 新增每日统计记录
     * @param statistics
     */
    Boolean insertDailyStatistics(Statistics statistics);

    /**
     * 增加每日访问量
     */
    void increaseDailyViews();

    /**
     * 增加每月访问量
     * @param views     访问量
     * @return
     */
    Boolean increaseMonthlyViews(Integer views);

    /**
     * 查询今日访问量
     * @return
     */
    Statistics queryTodayViews();

    /**
     * 查询昨日访问量
     * @return
     */
    Statistics queryYesterdayViews();

    /**
     * 查询本月访问量
     * @return
     */
    Statistics queryMonthViews();

    /**
     * 新增每月统计记录
     * @param statistics
     */
    Boolean insertMonthlyStatistics(Statistics statistics);

    /**
     * 新增每年统计记录
     * @param statistics
     */
    Boolean insertAnnualStatistics(Statistics statistics);

}
