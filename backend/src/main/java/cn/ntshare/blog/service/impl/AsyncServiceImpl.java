package cn.ntshare.blog.service.impl;

import cn.ntshare.blog.service.ArticleService;
import cn.ntshare.blog.service.AsyncService;
import cn.ntshare.blog.service.StatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created By Seven.wk
 * Description: 系统异步任务
 * Created At 2019/01/24
 */
@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private ArticleService articleService;

    @Override
    @Async("asyncServiceExecutor")
    public void increasePageViews(Integer id) {
        // 增加文章浏览量
        articleService.increasePageViews(id);
        // 增加访问量
        statisticsService.increaseDailyViews();
    }
}
