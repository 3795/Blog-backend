package com.seven.Blog.service;

import com.github.pagehelper.PageInfo;
import com.seven.Blog.dto.ArticleDTO;
import com.seven.Blog.pojo.Article;
import com.seven.Blog.vo.ServerResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 文章管理服务
 * Created At 2018/08/08
 */
public interface ArticleService {

    /**
     * 获得所有的文章
     * @param page
     * @param size
     * @return
     */
    List<Article> getAllArticles(Integer page, Integer size);

    /**
     * 获取所有满足状态条件的文章
     * @param status        文章的状态
     * @param page      当前页码
     * @param size      每页的条数
     * @return
     */
    List<Article> getArticlesByStatus(Integer status, Integer page, Integer size);

    Article getArticleByPrimaryKey(Integer id);

    Article getPublishedArticleByPrimaryKey(Integer id);

    ServerResponse changeArticleStatus(Integer id);

    ServerResponse addArticle(String title, String img, String summary, String content, String categoryId, String status);

    int getArticleCount();

    int getArticleCountByStatus(Integer status);

    int getArticleCountByCategoryId(Integer categoryId);

    ServerResponse deleteArticleByPrimaryKey(Integer id);

    ServerResponse updateArticle(Article article);

    List<Article> getAllPublishedArticle(Integer page, Integer size);

    List<Article> getPublishedArticleByCategoryIds(String sql, Integer page, Integer size);

    List<Article> getPublishedArticleByCategoryId(Integer categoryId, Integer page, Integer size);

    List<Article> getPublishedArticleByKeywords(String keywords, Integer page, Integer size);

    int getArticleCountByKeywords(String keywords);

    /*-----------------------------------二期新增-----------------------------*/

    PageInfo selectAll(int pageNum, int pageSize);

    ArticleDTO selectById(Integer id);

    PageInfo selectBriefInfo(int pageNum, int pageSize);

    PageInfo selectBriefInfoByStatus(Integer status, int pageNum, int pageSize);

    boolean insert(Article article);

    boolean update(Article article);

    boolean updateStatus(@Param("id") Integer id,
                         @Param("status") Integer status);

    boolean delete(Integer id);

}
