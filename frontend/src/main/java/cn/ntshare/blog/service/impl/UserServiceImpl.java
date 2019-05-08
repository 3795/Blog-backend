package cn.ntshare.blog.service.impl;

import cn.ntshare.blog.constant.SystemConstant;
import cn.ntshare.blog.dao.UserMapper;
import cn.ntshare.blog.dto.UserDTO;
import cn.ntshare.blog.enums.ResponseCodeEnum;
import cn.ntshare.blog.exception.SystemException;
import cn.ntshare.blog.service.UserService;
import cn.ntshare.blog.util.CookieUtil;
import cn.ntshare.blog.util.JsonUtil;
import cn.ntshare.blog.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

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

    @Override
    public UserDTO queryUserInfo(HttpServletRequest request) {
        String key = CookieUtil.readCookie(request, SystemConstant.LOGIN_TOKEN);
        String userJson = RedisUtil.get(key);
        UserDTO userDTO = JsonUtil.string2Obj(userJson, UserDTO.class);
        if (userDTO == null) {
            log.error("反序列化UserDTO对象失败，对象为空");
            throw new SystemException(ResponseCodeEnum.SERVER_ERROR);
        }
        return userDTO;
    }

    @Override
    public Integer queryUserId(HttpServletRequest request) {
        String token = CookieUtil.readCookie(request, SystemConstant.LOGIN_TOKEN);
        String userJson = RedisUtil.get(token);
        UserDTO userDTO = JsonUtil.string2Obj(userJson, UserDTO.class);
        if (userDTO == null) {
            log.error("反序列化User对象失败，User对象为空");
            throw new SystemException(ResponseCodeEnum.SERVER_ERROR);
        }

        return userDTO.getId();
    }

}
