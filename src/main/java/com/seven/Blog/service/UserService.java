package com.seven.Blog.service;

import com.seven.Blog.pojo.User;
import com.seven.Blog.response.ServerResponse;

/**
 * Created By Seven.wk
 * Description: 用户服务接口
 * Created At 2018/08/06
 */
public interface UserService {
    /**
     * 根据用户输入的账号和密码获取用户信息
     * @param account       账号
     * @param password      密码
     * @return      用户信息
     */
    ServerResponse checkLoginInfo(String account, String password);

    User getUser(Integer id);

    ServerResponse updateUser(Integer id, String username, String avatar);
}
