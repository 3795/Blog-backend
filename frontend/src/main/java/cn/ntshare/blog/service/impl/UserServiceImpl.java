package cn.ntshare.blog.service.impl;

import cn.ntshare.blog.dao.UserMapper;
import cn.ntshare.blog.dto.UserDTO;
import cn.ntshare.blog.enums.ResponseCodeEnum;
import cn.ntshare.blog.exception.SystemException;
import cn.ntshare.blog.service.UserService;
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
    public UserDTO queryUserById(Integer id) {
        UserDTO userDTO = userMapper.selectById(id);
        if (userDTO == null) {
            log.warn("用户{}不存在", id);
            throw new SystemException(ResponseCodeEnum.USER_NOT_EXISTS);
        }
        return userMapper.selectById(id);
    }

}
