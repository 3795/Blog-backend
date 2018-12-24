package com.seven.Blog.controller.api.v1.frontend;

import com.github.pagehelper.PageInfo;
import com.seven.Blog.dto.ArticleDTO;
import com.seven.Blog.enums.CommonStatusEnum;
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
@RequestMapping("/article")
public class FArticleControllerV1 {

    @Autowired
    private ArticleService articleService;

    /**
     * 获取所有的文章
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping
    public ServerResponse queryArticles(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        PageInfo pageInfo = articleService.selectPublished(pageNum, pageSize);
        return ServerResponse.success(pageInfo);
    }

    /**
     * 根据文章ID查找文章信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ServerResponse queryArticleById(@PathVariable("id") Integer id) {
        ArticleDTO article = articleService.selectById(id);
        return ServerResponse.success(article);
    }

    /**
     * 根据文章id查找文章的简要信息
     * @param id
     * @return
     */
    @GetMapping("/{id}/brief")
    public ServerResponse queryBriefInfoById(@PathVariable("id") Integer id) {
        ArticleDTO article = articleService.queryBriefInfoById(id);
        return ServerResponse.success(article);
    }

    /**
     * 根据文章id查找文章的内容
     * @param id
     * @return
     */
    @GetMapping("/{id}/content")
    public ServerResponse queryContentById(@PathVariable("id") Integer id) {
        String content = articleService.queryContentById(id);
        return ServerResponse.success(content);
    }

    /**
     * 根据关键字查找文章
     * @param keywords
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/search")
    public ServerResponse search(@RequestParam("keywords") String keywords,
                                 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                 @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        PageInfo pageInfo = articleService.search(keywords, pageNum, pageSize);
        return ServerResponse.success(pageInfo);
    }

    /**
     * 统计文章数量
     * @return
     */
    @GetMapping("/count")
    public ServerResponse count() {
        int count = articleService.countByStatusAndType(CommonStatusEnum.ON.getCode(), null);
        return ServerResponse.success(count);
    }
}
