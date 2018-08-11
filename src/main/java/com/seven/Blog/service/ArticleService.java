package com.seven.Blog.service;

import com.seven.Blog.pojo.Article;
import com.seven.Blog.response.ServerResponse;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 文章管理服务
 * Created At 2018/08/08
 */
public interface ArticleService {

    List<Article> getAllArticles(Integer page, Integer size);

    Article getArticleByPrimaryKey(Integer id);

    Article getPublishedArticleByPrimaryKey(Integer id);

    ServerResponse changeArticleStatus(Integer id);

    ServerResponse addArticle(String title, String img, String summary, String content, String categoryId, String status);

    int getArticleCount();

    int getArticleCount(Integer status);

    ServerResponse deleteArticleByPrimaryKey(Integer id);

    ServerResponse updateArticle(Article article);

    List<Article> getAllPublishedArticle(Integer page, Integer size);
}
