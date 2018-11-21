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

    private Integer id;     //分类id

    private String name;        //分类名称

    private Integer parentId;    //父类id

    private Integer status;     //分类状态,0为不可用，1为可用

    public Category(String name, Integer parentId, Integer status) {
        this.name = name;
        this.parentId = parentId;
        this.status = status;
    }
}
