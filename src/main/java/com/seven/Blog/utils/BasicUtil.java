package com.seven.Blog.utils;

/**
 * Created By Seven.wk
 * Description: 基本的工具方法
 * Created At 2018/08/10
 */
public class BasicUtil {

    /**
     * 获取当前的分页页码
     * @param page
     * @param maxPage
     * @return
     */
    public static Integer getPage(Integer page, Integer maxPage) {
        if(page < 1)
            page = 1;
        if(page > maxPage && maxPage > 0)
            page = maxPage;
        return page;
    }
}
