package com.seven.Blog.controller.api.v1.frontend;

import com.github.pagehelper.PageInfo;
import com.seven.Blog.dto.ArticleDTO;
import com.seven.Blog.service.ArticleService;
import com.seven.Blog.vo.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created By Seven.wk
 * Description: 文章信息接口
 * Created At 2018/11/15
 */
@RestController
@RequestMapping("/blog/v1/article")
public class FArticleControllerV1 {

    @Autowired
    private ArticleService articleService;

    @GetMapping
    public ServerResponse getAllArticles(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        PageInfo pageInfo = articleService.selectPublished(pageNum, pageSize);
        return ServerResponse.success(pageInfo);
    }


    @GetMapping("/{id}")
    public ServerResponse getArticleById(@PathVariable("id") Integer id) {
        ArticleDTO article = articleService.selectById(id);
        return ServerResponse.success(article);
    }

    @GetMapping("/search")
    public ServerResponse search(@RequestParam("keywords") String keywords,
                                 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                 @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        PageInfo pageInfo = articleService.search(keywords, pageNum, pageSize);
        return ServerResponse.success(pageInfo);
    }
}
