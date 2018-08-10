package com.seven.Blog.pojo;

/**
 * Created By Seven.wk
 * Description: 前端导航类实体
 * Created At 2018/08/10
 */
public class Navigation {

    private Integer id;

    private String name;

    private Integer priority;

    private String link;

    private Integer status;

    public Navigation() {
    }

    public Navigation(Integer id, String name, Integer priority, String link, Integer status) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.link = link;
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
