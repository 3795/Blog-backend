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

    PageInfo selectBriefInfoByTypeAndStatus(Integer type, Integer status, int pageNum, int pageSize);

    void insert(Article article, List<Integer> tags);

    void update(Article article, List<Integer> tags);

    void updateStatus(Integer id, Integer status);

    void updateType(Integer id, Integer type);

    void delete(Integer id);

    int countAll();

    int countByStatusAndType(Integer status, Integer type);

    /**
     * 增加文章浏览量
     * @param id
     */
    void increasePageViews(Integer id);
}
