package com.seven.Blog.service;

import com.seven.Blog.dto.UserDTO;
import com.seven.Blog.pojo.User;

/**
 * Created By Seven.wk
 * Description: 用户服务接口
 * Created At 2018/08/06
 */
public interface UserService {

    User checkLoginInfo(String account, String password);

    UserDTO getUser(Integer id);

    Boolean updateInfo(User user);
}
