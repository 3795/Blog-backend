package cn.ntshare.Blog.service;

import cn.ntshare.Blog.pojo.Message;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 系统消息服务
 * Created At 2019/01/11
 */
public interface MessageService {

    List<Message> queryByStatus(Integer status);

    void insert(String title, String content);

    void updateStatus(Integer id);

    void delete(Integer id);

    Integer countByStatus(Integer status);

    /**
     * 清空已读消息
     */
    void empty();
}
