package com.seven.Blog.pojo;

import lombok.*;

/**
 * Created By Seven.wk
 * Description: 文章分类实体
 * Created At 2018/08/07
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Category {

    private Integer id;

    private String name;

    private Integer parentId;

    private Integer status;

    public Category(String name, Integer parentId, Integer status) {
        this.name = name;
        this.parentId = parentId;
        this.status = status;
    }
}
