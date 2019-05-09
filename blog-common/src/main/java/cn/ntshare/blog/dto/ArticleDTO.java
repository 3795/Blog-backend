package cn.ntshare.blog.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * Created By Q.Hao
 * Description:
 * Created At 2019/5/7
 */
@Data
@ToString(exclude = {"summary", "content"})
public class ArticleDTO {
    private Integer id;

    private String title;

    private String img;

    private String summary;

    private String content;

    private Integer categoryId;

    private String categoryName;

    private Integer status;

    private Integer type;

    private Date createTime;

    private Date updateTime;

    private List<TagDTO> tags;

    private Integer pageviews;
}
