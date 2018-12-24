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

    PageInfo selectBriefInfoByTypeAndStatus(Integer type, Integer status, int pageNum, int pageSize);

    boolean insert(Article article);

    boolean update(Article article);

    boolean updateStatus(Integer id, Integer status);

    boolean updateType(Integer id, Integer type);

    boolean delete(Integer id);

    PageInfo selectPublished(int pageNum, int pageSize);

    PageInfo search(String keywords, int pageNum, int pageSize);

    PageInfo selectPublishedByCate(Integer categoryId, int pageNum, int pageSize);

    int countAll();

    int countByStatusAndType(Integer status, Integer type);

    ArticleDTO queryBriefInfoById(Integer id);

    String queryContentById(Integer id);
}
