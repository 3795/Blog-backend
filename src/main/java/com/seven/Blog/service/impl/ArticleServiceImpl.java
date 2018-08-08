package com.seven.Blog.service.impl;

import com.seven.Blog.dao.ArticleMapper;
import com.seven.Blog.pojo.Article;
import com.seven.Blog.response.ServerResponse;
import com.seven.Blog.service.ArticleService;
import com.seven.Blog.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 文章管理服务实现
 * Created At 2018/08/08
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<Article> getAllArticles() {
        return articleMapper.getAllArticles();
    }

    @Override
    public Article getArticleByPrimaryKey(Integer id) {
        return articleMapper.selectedArticleByPrimaryKey(id);
    }

    @Override
    public ServerResponse changeArticleStatus(Integer id) {
        Article article = getArticleByPrimaryKey(id);
        int status;
        if(article.getStatus() == Const.ArticleStatus.PUBLISHED.getCode())
            status = Const.ArticleStatus.UNPUBLISHED.getCode();
        else
            status = Const.ArticleStatus.PUBLISHED.getCode();
        int result = articleMapper.updateArticleStatusByPrimaryKey(id, status);
        if(result == 1)
            return ServerResponse.success("更改状态成功");
        return ServerResponse.success("更改状态失败");
    }
}
