package cn.ntshare.blog.service;

/**
 * Created By Seven.wk
 * Description: 系统异步服务
 * Created At 2019/01/24
 */
public interface AsyncService {

    /**
     * 增加文章浏览量和系统访问量
     * @param id
     */
    void increasePageViews(Integer id);
}
