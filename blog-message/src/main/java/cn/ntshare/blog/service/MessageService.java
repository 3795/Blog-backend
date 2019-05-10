package cn.ntshare.blog.service;

import java.util.Date;

/**
 * Created By Seven.wk
 * Description: 系统消息服务
 * Created At 2019/01/11
 */
public interface MessageService {

    void insert(String title, String content, Date time);

}
