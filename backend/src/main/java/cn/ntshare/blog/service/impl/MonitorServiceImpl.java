package cn.ntshare.blog.service.impl;

import cn.ntshare.blog.dto.MonitorDTO;
import cn.ntshare.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created By Seven.wk
 * Description: 系统检测服务实现
 * Created At 2019/01/06
 */
@Service
public class MonitorServiceImpl implements MonitorService {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @Autowired
    private StatisticsService statisticsService;

    @Override
    public MonitorDTO queryData() {
        int articleCount = articleService.countAll();
        int categoryCount = categoryService.count();
        int tagCount = tagService.count();
        int todayViews = statisticsService.queryTodayViews().getViews();
        int yesterdayViews = statisticsService.queryYesterdayViews().getViews();
        int monthViews = statisticsService.queryMonthViews().getViews();
        return new MonitorDTO(articleCount, categoryCount, tagCount, todayViews, yesterdayViews, monthViews);
    }
}
