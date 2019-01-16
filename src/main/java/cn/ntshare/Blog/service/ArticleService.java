package cn.ntshare.Blog.service;

import cn.ntshare.Blog.dto.ArticleDTO;
import cn.ntshare.Blog.pojo.Article;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 文章管理服务
 * Created At 2018/08/08
 */
public interface ArticleService {

    PageInfo selectAll(int pageNum, int pageSize);

    ArticleDTO selectById(Integer id);

    PageInfo selectBriefInfoByTypeAndStatus(Integer type, Integer status, int pageNum, int pageSize);

    void insert(Article article, List<Integer> tags);

    void update(Article article, List<Integer> tags);

    void updateStatus(Integer id, Integer status);

    void updateType(Integer id, Integer type);

    void delete(Integer id);

    PageInfo selectPublished(int pageNum, int pageSize);

    PageInfo search(String keywords, int pageNum, int pageSize);

    PageInfo selectPublishedByCate(Integer categoryId, int pageNum, int pageSize);

    int countAll();

    int countByStatusAndType(Integer status, Integer type);

    ArticleDTO queryBriefInfoById(Integer id);

    String queryContentById(Integer id);
}
