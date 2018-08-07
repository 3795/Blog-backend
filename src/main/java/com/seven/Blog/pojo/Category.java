package com.seven.Blog.pojo;

/**
 * Created By Seven.wk
 * Description: 文章分类实体
 * Created At 2018/08/07
 */
public class Category {

    private Integer id;     //分类id

    private String name;        //分类名称

    private Integer parentId;    //父类id

    private Integer status;     //分类状态,0为不可用，1为可用

    public Category() {
    }

    public Category(Integer id, String name, Integer parentId, Integer status) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.status = status;
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
