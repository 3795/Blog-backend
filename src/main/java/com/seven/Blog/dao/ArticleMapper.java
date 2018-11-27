package com.seven.Blog.dao;

import com.seven.Blog.dto.ArticleDTO;
import com.seven.Blog.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 文章信息Dao层
 * Created At 2018/08/08
 */
@Repository
@Mapper
public interface ArticleMapper {

    /**
     * 新增一篇文章
     * @param title
     * @param img
     * @param summary
     * @param content
     * @param categoryId
     * @param status
     * @return
     */
    int addArticle(@Param("title") String title,
                   @Param("img") String img,
                   @Param("summary") String summary,
                   @Param("content") String content,
                   @Param("categoryId") String categoryId,
                   @Param("status") String status);

    /**
     * 根据主键查找文章
     * @param id
     * @return
     */
    Article selectedArticleByPrimaryKey(Integer id);

    /**
     * 通过主键查找已发布的文章
     * @param id
     * @return
     */
    Article selectedPublishedArticleByPrimaryKey(Integer id);

    /**
     * 获取所有的文章
     * @param offset        偏移量
     * @param size      每页文章的数量
     * @return
     */
    List<Article> getAllArticles(@Param("offset") Integer offset,
                                 @Param("size") Integer size);

    /**
     * 得到满足状态要求的文章
     * @param status        装态
     * @param offset        偏移量
     * @param size      每页文章的数量
     * @return
     */
    List<Article> getArticlesByStatus(@Param("status") Integer status,
                                      @Param("offset") Integer offset,
                                      @Param("size") Integer size);

    /**
     * 更新一篇文章
     * @param article
     * @return
     */
    int updateArticle(Article article);

    /**
     * 根据主键的值删除一篇文章
     * @param id
     * @return
     */
    int deleteArticleByPrimaryKey(Integer id);

    /**
     * 根据主键的值来改变该文章的状态
     * @param id
     * @param status
     * @return
     */
    int updateArticleStatusByPrimaryKey(@Param("id") Integer id,
                                        @Param("status") Integer status);

    /**
     * 获得文章的总数
     * @return
     */
    int getArticleCount();

    /**
     * 获得已发布文章的总数
     * @param status
     * @return
     */
    int getArticleCountByStatus(Integer status);

    /**
     * 获得在某一分类下已发布文章的总数
     * @param categoryId
     * @return
     */
    int getPublishedArticleCountByCategoryId(Integer categoryId);

    /**
     * 得到所有已发布的文章
     * @param offset
     * @param size
     * @return
     */
    List<Article> getAllPublishedArticles(@Param("offset") Integer offset,
                                          @Param("size") Integer size);

    /**
     *
     * 得到一些分类下所有已发布的文章
     * @param categoryIds
     * @param offset
     * @param size
     * @return
     */
    List<Article> getPublishedArticlesByCategoryIds(@Param("categoryIds") String[] categoryIds,
                                                 @Param("offset") Integer offset,
                                                 @Param("size") Integer size);

    /**
     * 得到某个分类下所有已发布的文章
     * @param categoryId
     * @param offset
     * @param size
     * @return
     */
    List<Article> getPublishedArticlesByCategoryId(@Param("categoryId") Integer categoryId,
                                                    @Param("offset") Integer offset,
                                                    @Param("size") Integer size);

    /**
     * 通过关键字查询已发布的文章
     * @param keywords
     * @param offset
     * @param size
     * @return
     */
    List<Article> getPublishedArticleByKeywords(@Param("keywords") String keywords,
                                                @Param("offset") Integer offset,
                                                @Param("size") Integer size);

    /**
     * 通过关键字得到已发布文章的数量
     * @param keywords
     * @return
     */
    int getPublishedArticleCountByKeywords(String keywords);

    /*----------------------------二期新增------------------------------*/

    List<ArticleDTO> selectAll();

    ArticleDTO selectById(Integer id);

    List<ArticleDTO> selectBriefInfoByStatus(Integer status);

    int insert(Article article);

    int update(Article article);

    int updateStatus(@Param("id") Integer id,
                     @Param("status") Integer status);

    int delete(Integer id);

    List<ArticleDTO> selectPublished();

    List<ArticleDTO> search(String keywords);

}
