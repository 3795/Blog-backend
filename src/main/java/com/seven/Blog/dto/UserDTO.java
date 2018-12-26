package com.seven.Blog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created By Seven.wk
 * Description: 用户信息转换
 * Created At 2018/10/02
 */
@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private Integer id;

    private String account;

    private String username;

    private String avatar;

    private String email;

    private String phone;

    private String signature;

    public UserDTO(String username, String avatar) {
        this.username = username;
        this.avatar = avatar;
    }

}
