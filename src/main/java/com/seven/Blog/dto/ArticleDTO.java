package com.seven.Blog.dto;

import java.util.Date;

/**
 * Created By Seven.wk
 * Description: 文章信息传输模型
 * Created At 2018/08/08
 */
public class ArticleDTO {
    private Integer id;     //文章id

    private String title;       //文章标题

    private String img;         //文章主图地址

    private String summary;     //文章摘要

    private String content;     //文章内容

    private String categoryName;     //文章分类名称

    private String statusMsg;     //文章状态码,0为草稿，1为发表，2为回收

    private Date createTime;        //创建时间

    private Date updateTime;        //更新时间

    public ArticleDTO() {
    }

    public ArticleDTO(Integer id, String title, String img, String summary, String content, Date createTime, Date updateTime) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.summary = summary;
        this.content = content;
        this.createTime = createTime;
        this.updateTime = updateTime;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
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
