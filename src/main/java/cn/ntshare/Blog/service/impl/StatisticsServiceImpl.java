package cn.ntshare.Blog.service.impl;

import cn.ntshare.Blog.dao.StatisticsMapper;
import cn.ntshare.Blog.pojo.Statistics;
import cn.ntshare.Blog.service.StatisticsService;
import cn.ntshare.Blog.util.CalendarUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created By Seven.wk
 * Description: 实现层
 * Created At 2019/01/16
 */
@Service
@Slf4j
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private StatisticsMapper statisticsMapper;

    @Override
    public Boolean insertDailyStatistics(Statistics statistics) {
        int result = statisticsMapper.insert(statistics);
        if (result != 1) {
            log.error("insert statistics error");
            return false;
        }
        return true;
    }

    @Override
    public void increaseDailyViews() {
        int result = statisticsMapper.increaseDailyViews(CalendarUtil.getToday());
        if (result != 1) {
            log.error("update statistics error");
        }
    }

    @Override
    public Boolean increaseMonthlyViews(Integer views) {
        String month = CalendarUtil.getCurrentMonth();
        int result = statisticsMapper.increaseMonthlyViews(month, views);
        if (result != 1) {
            log.error("update statistics error");
            return false;
        }
        return true;
    }

    @Override
    public Statistics queryTodayViews() {
        return statisticsMapper.queryDailyViews(CalendarUtil.getToday());
    }

    @Override
    public Statistics queryYesterdayViews() {
        return statisticsMapper.queryDailyViews(CalendarUtil.getYesterday());
    }

    @Override
    public Statistics queryMonthViews() {
        return statisticsMapper.queryMonthViews(CalendarUtil.getCurrentMonth());
    }

    @Override
    public Boolean insertMonthlyStatistics(Statistics statistics) {
        int result = statisticsMapper.insert(statistics);
        if (result != 1) {
            log.error("insert statistics error");
            return false;
        }
        return true;
    }

    @Override
    public Boolean insertAnnualStatistics(Statistics statistics) {
        int result = statisticsMapper.insert(statistics);
        if (result != 1) {
            log.error("insert statistics error");
            return false;
        }
        return true;
    }

}
