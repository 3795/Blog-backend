package com.seven.Blog.dto;

/**
 * Created By Seven.wk
 * Description: 用户信息转换
 * Created At 2018/10/02
 */
public class UserDTO {

    private Integer id;

    private String username;

    private String avatar;

    public UserDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
