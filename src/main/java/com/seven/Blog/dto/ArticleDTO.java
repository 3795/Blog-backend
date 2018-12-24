package com.seven.Blog.dto;

import lombok.*;

import java.util.Date;

/**
 * Created By Seven.wk
 * Description: 文章信息传输模型
 * Created At 2018/08/08
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

}
