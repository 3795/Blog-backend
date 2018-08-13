package com.seven.Blog.dao;

import com.seven.Blog.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 文章信息Dao层
 * Created At 2018/08/08
 */
@Mapper
public interface ArticleMapper {

    int addArticle(@Param("title") String title,
                   @Param("img") String img,
                   @Param("summary") String summary,
                   @Param("content") String content,
                   @Param("categoryId") String categoryId,
                   @Param("status") String status);

    Article selectedArticleByPrimaryKey(Integer id);

    Article selectedPublishedArticleByPrimaryKey(Integer id);

    List<Article> getAllArticles(@Param("offset") Integer offset,
                                 @Param("size") Integer size);

    int updateArticle(Article article);

    int deleteArticleByPrimaryKey(Integer id);

    int updateArticleStatusByPrimaryKey(@Param("id") Integer id,
                                        @Param("status") Integer status);

    int getArticleCount();

    int getPublishedArticleCount(Integer status);

    int getPublishedArticleCountByCategoryId(Integer categoryId);

    List<Article> getAllPublishedArticles(@Param("offset") Integer offset,
                                          @Param("size") Integer size);

    List<Article> getPublishedArticlesByCategoryIds(@Param("categoryIds") String[] categoryIds,
                                                 @Param("offset") Integer offset,
                                                 @Param("size") Integer size);

    List<Article> getPublishedArticlesByCategoryId(@Param("categoryId") Integer categoryId,
                                                    @Param("offset") Integer offset,
                                                    @Param("size") Integer size);
}
