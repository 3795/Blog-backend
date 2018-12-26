package com.seven.Blog.service.impl;

import com.seven.Blog.Exception.SystemException;
import com.seven.Blog.dao.UserMapper;
import com.seven.Blog.dto.UserDTO;
import com.seven.Blog.enums.ResponseCodeEnum;
import com.seven.Blog.pojo.User;
import com.seven.Blog.service.UserService;
import com.seven.Blog.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created By Seven.wk
 * Description: 用户信息服务接口实现
 * Created At 2018/08/06
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkLoginInfo(String account, String password) {
        password = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.selectByAccountAndPassword(account, password);
        if(user == null) {
            log.warn("账号：{}", account);
            throw new SystemException(ResponseCodeEnum.LOGIN_FAILED);
        }
        return user;
    }

    @Override
    public UserDTO getUser(Integer id) {
        UserDTO userDTO = userMapper.selectById(id);
        if (userDTO == null) {
            log.warn("用户{}不存在", id);
            throw new SystemException(ResponseCodeEnum.USER_NOT_EXISTS);
        }
        return userMapper.selectById(id);
    }

    @Override
    public Boolean updateInfo(User user) {
        int result = userMapper.updateInfo(user);
        if (result != 1) {
            throw new SystemException(ResponseCodeEnum.UPDATE_FAILED);
        }
        return true;
    }
}
