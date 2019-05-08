package cn.ntshare.blog.dao;

import cn.ntshare.blog.dto.ArticleDTO;
import cn.ntshare.blog.dto.TagDTO;
import cn.ntshare.blog.pojo.Article;
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

    List<ArticleDTO> selectAll();

    ArticleDTO selectById(Integer id);

    List<ArticleDTO> selectBriefInfoByTypeAndStatus(@Param("type") Integer type,
                                                    @Param("status") Integer status);

    int insert(Article article);

    int update(Article article);

    int updateStatus(@Param("id") Integer id,
                     @Param("status") Integer status);

    int updateType(@Param("id") Integer id,
                   @Param("type") Integer type);

    int delete(Integer id);

    List<ArticleDTO> selectPublished();

    List<ArticleDTO> search(String keywords);

    List<ArticleDTO> selectPublishedByCategories(List<Integer> idList);

    List<ArticleDTO> selectPublishedByCategoryId(Integer id);

    ArticleDTO queryBriefInfoById(Integer id);

    String queryContentById(Integer id);

    int count();

    int countByStatus(@Param("status") Integer status,
                      @Param("type") Integer type);

    int insertArticleTag(@Param("articleId") Integer articleId,
                         @Param("tags") List<Integer> tags);

    List<Integer> queryTagIdByArticleId(@Param("articleId") Integer articleId);

    int deleteArticleTagByArticleId(@Param("articleId") Integer articleId);

    List<TagDTO> queryTagsById(Integer id);

    int insertArticlePageViews(@Param("articleId") Integer articleId,
                               @Param("pageViews") int pageViews);

    int deleteArticlePageViews(@Param("articleId") Integer articleId);

    int increasePageViews(@Param("articleId") Integer articleId);
}
