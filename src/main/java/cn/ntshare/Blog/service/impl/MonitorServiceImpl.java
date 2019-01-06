package cn.ntshare.Blog.service.impl;

import cn.ntshare.Blog.dto.MonitorDTO;
import cn.ntshare.Blog.service.ArticleService;
import cn.ntshare.Blog.service.CategoryService;
import cn.ntshare.Blog.service.MonitorService;
import cn.ntshare.Blog.service.TagService;
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

    @Override
    public MonitorDTO queryData() {
        int articleCount = articleService.countAll();
        int categoryCount = categoryService.count();
        int tagCount = tagService.count();
        return new MonitorDTO(articleCount, categoryCount, tagCount);
    }
}
