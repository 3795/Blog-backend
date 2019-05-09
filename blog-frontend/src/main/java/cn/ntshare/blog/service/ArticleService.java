package cn.ntshare.blog.service;

import cn.ntshare.blog.dto.ArticleDTO;
import cn.ntshare.blog.pojo.Article;
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

    PageInfo selectPublished(int pageNum, int pageSize);

    PageInfo search(String keywords, int pageNum, int pageSize);

    PageInfo selectPublishedByCate(Integer categoryId, int pageNum, int pageSize);

    int countByStatusAndType(Integer status, Integer type);

    ArticleDTO queryBriefInfoById(Integer id);

    String queryContentById(Integer id);

    /**
     * 增加文章浏览量
     * @param id
     */
    void increasePageViews(Integer id);
}
