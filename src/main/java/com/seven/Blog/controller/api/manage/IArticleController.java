package com.seven.Blog.controller.api.manage;

import com.seven.Blog.form.ArticleForm;
import com.seven.Blog.pojo.Article;
import com.seven.Blog.response.ServerResponse;
import com.seven.Blog.service.ArticleService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.validation.Valid;

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

    /**
     * 根据文章id修改一个文章的状态
     * @param id
     * @return
     */
    @PatchMapping("/{id}")
    public ServerResponse checkArticleStatus(@PathVariable("id") Integer id) {
        return articleService.changeArticleStatus(id);
    }

    /**
     * 添加一篇文章
     * @param articleForm
     * @param result
     * @return
     */
    @PostMapping("")
    public ServerResponse addArticle(@Valid ArticleForm articleForm,
                                     BindingResult result) {
        if(result.hasErrors()) {
            return ServerResponse.error(result.getFieldError().getDefaultMessage());
        }
        return articleService.addArticle(articleForm.getTitle(),
                articleForm.getImg(), articleForm.getSummary(),
                articleForm.getContent(), articleForm.getCategoryId(),
                articleForm.getStatus());
    }

    /**
     * 根据文章id值删除一个文章
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public ServerResponse deleteArticle(@PathVariable("id") Integer id) {
        return articleService.deleteArticleByPrimaryKey(id);
    }

    /**
     * 更新一篇文章
     * @param articleForm
     * @param result
     * @return
     */
    @PutMapping("")
    public ServerResponse updateArticle(@Valid ArticleForm articleForm,
                                        BindingResult result) {
        if(result.hasErrors()) {
            return ServerResponse.error(result.getFieldError().getDefaultMessage());
        }
        int id= Integer.parseInt(articleForm.getId());
        int categoryId= Integer.parseInt(articleForm.getCategoryId());
        int status= Integer.parseInt(articleForm.getStatus());
        Article article = new Article(id, articleForm.getTitle(),
                articleForm.getImg(), articleForm.getSummary(),
                articleForm.getContent(), categoryId, status);
        return articleService.updateArticle(article);
    }

}
