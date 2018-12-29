package com.seven.Blog.controller.api.v1.backend;

import com.github.pagehelper.PageInfo;
import com.seven.Blog.dto.ArticleDTO;
import com.seven.Blog.enums.ResponseCodeEnum;
import com.seven.Blog.form.ArticleForm;
import com.seven.Blog.pojo.Article;
import com.seven.Blog.service.ArticleService;
import com.seven.Blog.vo.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created By Seven.wk
 * Description: 文章管理控制器
 * Created At 2018/11/11
 */
@RestController
@RequestMapping("/backend/article")
public class BArticleControllerV1 {

    @Autowired
    private ArticleService articleService;

    /**
     * 根据文章类型(type)和状态(status)获取文章列表
     * @param type
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping
    public ServerResponse getArticles(@RequestParam(value = "status", defaultValue = "1") Integer status,
                                      @RequestParam(value = "type", required = false) Integer type,
                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

        PageInfo pageInfo = articleService.selectBriefInfoByTypeAndStatus(type, status, pageNum, pageSize);
        return ServerResponse.success(pageInfo);
    }

    /**
     * 根据ID值查看文章
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ServerResponse getArticleById(@PathVariable("id") Integer id) {
        ArticleDTO articleDTO = articleService.selectById(id);
        return ServerResponse.success(articleDTO);
    }

    /**
     * 创建文章
     * @param articleForm
     * @param result
     * @return
     */
    @PostMapping
    public ServerResponse create(@Valid ArticleForm articleForm,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.error(result.getFieldError().getDefaultMessage());
        }

        Article article = new Article(articleForm.getTitle(), articleForm.getImg(), articleForm.getSummary(),
                articleForm.getContent(), Integer.parseInt(articleForm.getCategoryId()),
                Integer.parseInt(articleForm.getType()));

        articleService.insert(article, articleForm.getTags());

        return ServerResponse.success(ResponseCodeEnum.INSERT_SUCCESS);
    }

    /**
     * 更新文章
     * @param id
     * @param articleForm
     * @param result
     * @return
     */
    @PutMapping
    public ServerResponse update(@RequestParam("id") Integer id,
                                 @Valid ArticleForm articleForm,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.error(result.getFieldError().getDefaultMessage());
        }

        Article article = new Article(id, articleForm.getTitle(), articleForm.getImg(),
                articleForm.getSummary(), articleForm.getContent(),
                Integer.parseInt(articleForm.getCategoryId()),
                Integer.parseInt(articleForm.getType()));

        articleService.update(article, articleForm.getTags());

        return ServerResponse.success(ResponseCodeEnum.UPDATE_SUCCESS);
    }

    /**
     * 更改文章状态
     * @param id
     * @param status
     * @return
     */
    @PatchMapping("/status")
    public ServerResponse updateStatus(@RequestParam("id") Integer id,
                                       @RequestParam("status") Integer status) {
        articleService.updateStatus(id, status);
        return ServerResponse.success(ResponseCodeEnum.UPDATE_SUCCESS);
    }

    /**
     * 更新文章状态
     * @param id
     * @param type
     * @return
     */
    @PatchMapping("/type")
    public ServerResponse updateType(@RequestParam("id") Integer id,
                                     @RequestParam("type") Integer type) {
        articleService.updateType(id, type);
        return ServerResponse.success(ResponseCodeEnum.UPDATE_SUCCESS);
    }

    /**
     * 删除文章
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ServerResponse delete(@PathVariable("id") Integer id) {
        articleService.delete(id);
        return ServerResponse.success(ResponseCodeEnum.DELETE_SUCCESS);
    }

    /**
     * 统计文章的数量
     * @param status
     * @return
     */
    @GetMapping("/count")
    public ServerResponse count(@RequestParam(value = "status", defaultValue = "-1") Integer status,
                                @RequestParam(value = "type", required = false) Integer type) {
        int result;
        if (status == -1) {
            result = articleService.countAll();
        } else {
            result = articleService.countByStatusAndType(status, type);
        }
        return ServerResponse.success(result);
    }

}
