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

    List<ArticleDTO> selectPublishedByCategories(List<Integer> idList);

    List<ArticleDTO> selectPublishedByCategoryId(Integer id);

    int count();

    int countByStatus(Integer status);

}
