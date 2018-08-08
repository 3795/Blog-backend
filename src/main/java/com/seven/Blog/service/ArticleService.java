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

    List<Article> getAllArticles();

    Article getArticleByPrimaryKey(Integer id);

    ServerResponse changeArticleStatus(Integer id);
}
