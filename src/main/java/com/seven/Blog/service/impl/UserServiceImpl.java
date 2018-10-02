package com.seven.Blog.service.impl;

import com.seven.Blog.dao.UserMapper;
import com.seven.Blog.enums.ResponseCodeEnum;
import com.seven.Blog.pojo.User;
import com.seven.Blog.vo.ServerResponse;
import com.seven.Blog.service.UserService;
import com.seven.Blog.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created By Seven.wk
 * Description: 用户信息服务接口实现
 * Created At 2018/08/06
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse checkLoginInfo(String account, String password) {
        password = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.selectByAccountAndPassword(account, password);
        if(user == null)
            return ServerResponse.error(ResponseCodeEnum.LOGIN_FAILED);
        return ServerResponse.success(ResponseCodeEnum.LOGIN_SUCCESS, user.getId());
    }

    @Override
    public User getUser(Integer id) {
        return userMapper.selectedUserByPrimaryKey(id);
    }

    @Override
    public ServerResponse updateUser(Integer id, String username, String avatar) {
        int result = userMapper.updateUser(id, username, avatar);
        if(result == 1)
            return ServerResponse.success("更改信息成功");
        return ServerResponse.success("更改信息失败");
    }
}
