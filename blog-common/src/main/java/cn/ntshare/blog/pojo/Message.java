package cn.ntshare.blog.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Created By Q.Hao
 * Description: 消息实体
 * Created At 2019/4/25
 */
@Data
public class Message {
    private Integer id;

    private String title;

    private String content;

    private Date createTime = new Date();

    private Integer status = 0;

    public Message(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
