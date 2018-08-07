package com.seven.Blog.form;

import javax.validation.constraints.NotEmpty;

/**
 * Created By Seven.wk
 * Description: 文章分类信息表单
 * Created At 2018/08/07
 */
public class CategoryForm {

    @NotEmpty(message = "ID不能为空")
    private String id;     //分类id

    @NotEmpty(message = "分类名称不能为空")
    private String name;        //分类名称

    @NotEmpty(message = "父类ID不能为空")
    private String parentId;    //父类id

    @NotEmpty(message = "分类状态不能为空")
    private String status;     //分类状态,0为不可用，1为可用

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
