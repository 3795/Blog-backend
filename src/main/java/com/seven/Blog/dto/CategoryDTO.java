package com.seven.Blog.dto;

/**
 * Created By Seven.wk
 * Description: 分类传输模型
 * Created At 2018/10/12
 */
public class CategoryDTO {

    private Integer id;     //分类id

    private String name;        //分类名称

    public CategoryDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
