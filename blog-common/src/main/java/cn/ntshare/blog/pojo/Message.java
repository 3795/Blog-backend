package cn.ntshare.blog.pojo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created By Q.Hao
 * Description: 消息实体
 * Created At 2019/4/25
 */
@Data
public class Message implements Serializable {

    private static final long serialVersionUID = 5428369578032516348L;

    private Integer id;

    private String title;

    private String content;

    private Date createTime;

    private Integer status = 0;

    public Message(String title, String content, Date time) {
        this.title = title;
        this.content = content;
        this.createTime = time;
    }
}
