package com.seven.Blog.service;

import com.github.pagehelper.PageInfo;
import com.seven.Blog.dto.ArticleDTO;
import com.seven.Blog.pojo.Article;
import org.apache.ibatis.annotations.Param;

/**
 * Created By Seven.wk
 * Description: 文章管理服务
 * Created At 2018/08/08
 */
public interface ArticleService {

    PageInfo selectAll(int pageNum, int pageSize);

    ArticleDTO selectById(Integer id);

    PageInfo selectBriefInfoByStatus(Integer status, int pageNum, int pageSize);

    boolean insert(Article article);

    boolean update(Article article);

    boolean updateStatus(@Param("id") Integer id,
                         @Param("status") Integer status);

    boolean delete(Integer id);

    PageInfo selectPublished(int pageNum, int pageSize);

    PageInfo search(String keywords, int pageNum, int pageSize);

    PageInfo selectPublishedByCate(Integer categoryId, int pageNum, int pageSize);

}
