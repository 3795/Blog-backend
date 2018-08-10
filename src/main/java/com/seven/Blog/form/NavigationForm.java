package com.seven.Blog.form;

import javax.validation.constraints.NotEmpty;

/**
 * Created By Seven.wk
 * Description: 导航信息表单验证
 * Created At 2018/08/10
 */
public class NavigationForm {

    @NotEmpty(message = "id不能为空")
    private String id;

    @NotEmpty(message = "名称不能为空")
    private String name;

    @NotEmpty(message = "优先级不能为空")
    private String priority;

    @NotEmpty(message = "链接地址不能为空")
    private String link;

    @NotEmpty(message = "状态不能为空")
    private String status;

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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
