package com.seven.Blog.form;

import javax.validation.constraints.NotEmpty;

/**
 * Created By Seven.wk
 * Description: 文章表单
 * Created At 2018/08/09
 */
public class ArticleForm {

    @NotEmpty(message = "ID不能为空")
    private String id;     //文章id

    @NotEmpty(message = "标题不能为空")
    private String title;   //文章标题

    @NotEmpty(message = "图片路径不能为空")
    private String img;     //文章标签图路径

    @NotEmpty(message = "文章摘要不能为空")
    private String summary;     //文章摘要

    @NotEmpty(message = "文章内容不能为空")
    private String content;     //文章内容

    @NotEmpty(message = "文章分类不能为空")
    private String categoryId;      //文章所属分类

    @NotEmpty(message = "文章状态不能为空")
    private String status;      //文章状态

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
        if(summary.length() > 115)
            return summary.substring(0, 115) + "...";
        return summary + "...";
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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
