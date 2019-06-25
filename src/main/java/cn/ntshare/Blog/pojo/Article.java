package cn.ntshare.Blog.pojo;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Created By Seven.wk
 * Description: 文章实体类
 * Created At 2018/08/07
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Article implements Serializable {

    private static final long serialVersionUID = 3693581635584286115L;

    private Integer id;

    private String title;

    private String img;

    private String summary;

    private String content;

    private Integer categoryId;

    private Integer status = 1;

    private Integer type;

    private Date createTime;

    private Date updateTime;

    public Article(Integer id, String title, String img, String summary, String content, Integer categoryId, Integer type) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.summary = summary;
        this.content = content;
        this.categoryId = categoryId;
        this.type = type;
    }

    public Article(String title, String img, String summary, String content, Integer categoryId, Integer type) {
        this.title = title;
        this.img = img;
        this.summary = summary;
        this.content = content;
        this.categoryId = categoryId;
        this.type = type;
    }
}
