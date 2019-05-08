package cn.ntshare.blog.dao;

import cn.ntshare.blog.dto.ArticleDTO;
import cn.ntshare.blog.dto.TagDTO;
import cn.ntshare.blog.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By Q.Hao
 * Description: 文章Dao
 * Created At 2019/5/7
 */
@Repository
@Mapper
public interface ArticleMapper {

    List<ArticleDTO> selectAll();

    ArticleDTO selectById(Integer id);

    List<ArticleDTO> selectPublished();

    List<ArticleDTO> search(String keywords);

    List<ArticleDTO> selectPublishedByCategories(List<Integer> idList);

    List<ArticleDTO> selectPublishedByCategoryId(Integer id);

    ArticleDTO queryBriefInfoById(Integer id);

    String queryContentById(Integer id);

    int countByStatus(@Param("status") Integer status,
                      @Param("type") Integer type);

    List<TagDTO> queryTagsById(Integer id);

    int increasePageViews(@Param("articleId") Integer articleId);
}
