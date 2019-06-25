package cn.ntshare.Blog.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created By Seven.wk
 * Description: 用户实体类
 * Created At 2018/08/06
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = 7042601099301493590L;

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