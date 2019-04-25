package cn.ntshare.blog.pojo;

import lombok.Data;

/**
 * Created By Q.Hao
 * Description: 用户Model
 * Created At 2019/4/25
 */
@Data
public class User {
    private Integer id;

    private String account;

    private String username;

    private String avatar;

    private String password;

    private String email;

    private String phone;

    private String signature;

    public User(Integer id, String username, String avatar) {
        this.id = id;
        this.username = username;
        this.avatar = avatar;
    }
}
