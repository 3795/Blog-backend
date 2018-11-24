package com.seven.Blog.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created By Seven.wk
 * Description: 用户实体类
 * Created At 2018/08/06
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

    private Integer id;

    private String account;

    private String username;

    private String avatar;

    private String password;

    private Date createTime = new Date();


}
