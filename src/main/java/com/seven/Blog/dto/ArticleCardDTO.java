package com.seven.Blog.dto;

import java.util.Date;

/**
 * Created By Seven.wk
 * Description: 文章卡片DTO，给前端系统展示用
 * Created At 2018/10/04
 */
public class ArticleCardDTO {
    private Integer id;     //文章id

    private String title;       //文章标题

    private String img;         //文章主图地址

    private String summary;     //文章摘要

    private String categoryName;     //文章分类名称

    private Date createTime;        //创建时间



    public ArticleCardDTO() {
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
