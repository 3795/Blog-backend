package com.seven.Blog.controller.api.v1.frontend;

import com.github.pagehelper.PageInfo;
import com.seven.Blog.dto.ArticleDTO;
import com.seven.Blog.enums.CommonStatusEnum;
import com.seven.Blog.service.ArticleService;
import com.seven.Blog.vo.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created By Seven.wk
 * Description: 文章信息接口
 * Created At 2018/11/15
 */
@RestController
@RequestMapping("/article")
@Api(tags = "前台文章接口")
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
    @ApiOperation(value = "分页查询文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示数量", paramType = "query"),
    })
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
    @ApiOperation(value = "根据ID查询文章详情")
    @ApiImplicitParam(name = "id", value = "文章ID", required = true, paramType = "path")
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
    @ApiOperation(value = "根据ID查询文章简要信息")
    @ApiImplicitParam(name = "id", value = "文章ID", required = true, paramType = "path")
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
    @ApiOperation(value = "根据ID查询文章内容")
    @ApiImplicitParam(name = "id", value = "文章ID", required = true, paramType = "path")
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
    @ApiOperation(value = "根据关键字查询文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keywords", value = "关键字", required = true, paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页码", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示数量", paramType = "query"),
    })
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
    @ApiOperation(value = "统计文章数量")
    public ServerResponse count() {
        int count = articleService.countByStatusAndType(CommonStatusEnum.ON.getCode(), null);
        return ServerResponse.success(count);
    }
}
