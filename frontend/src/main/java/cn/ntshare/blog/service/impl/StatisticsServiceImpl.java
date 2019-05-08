package cn.ntshare.blog.service.impl;

import cn.ntshare.blog.dao.StatisticsMapper;
import cn.ntshare.blog.service.StatisticsService;
import cn.ntshare.blog.util.CalendarUtil;
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
    public void increaseDailyViews() {
        int result = statisticsMapper.increaseDailyViews(CalendarUtil.getToday());
        if (result != 1) {
            log.error("update statistics error");
        }
    }

}
