package cn.ntshare.Blog.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created By Seven.wk
 * Description: 系统消息实体类
 * Created At 2019/01/11
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
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
