package com.seven.Blog.pojo;

import java.util.Date;

/**
 * Created By Seven.wk
 * Description: 文章实体类
 * Created At 2018/08/07
 */
public class Article {

    private Integer id;     //文章id

    private String title;       //文章标题

    private String summary;     //文章摘要

    private String content;     //文章内容

    private Integer categoryId;     //文章分类id

    private Integer status;     //文章状态码,0为草稿，1为发表，2为回收

    private Date createTime = new Date();        //创建时间

    private Date updateTime = new Date();        //更新时间

    public Article() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
