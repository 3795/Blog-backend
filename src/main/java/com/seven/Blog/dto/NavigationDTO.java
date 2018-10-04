package com.seven.Blog.dto;

/**
 * Created By Seven.wk
 * Description: 导航信息传输模型
 * Created At 2018/10/04
 */
public class NavigationDTO {

    private String name;

    private String link;

    public NavigationDTO() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
