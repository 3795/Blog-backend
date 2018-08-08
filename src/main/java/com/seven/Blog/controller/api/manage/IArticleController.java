package com.seven.Blog.controller.api.manage;

import com.seven.Blog.response.ServerResponse;
import com.seven.Blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By Seven.wk
 * Description: 后台文章管理的api
 * Created At 2018/08/08
 */
@RestController
@RequestMapping("/api/manage/article")
public class IArticleController {

    @Autowired
    private ArticleService articleService;

    @PatchMapping("/{id}")
    public ServerResponse checkArticleStatus(@PathVariable("id") Integer id) {
        return articleService.changeArticleStatus(id);
    }
}
