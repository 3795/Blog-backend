package cn.ntshare.blog.service.impl;

import cn.ntshare.blog.client.FileClient;
import cn.ntshare.blog.constant.SystemConstant;
import cn.ntshare.blog.dao.UserMapper;
import cn.ntshare.blog.dto.UserDTO;
import cn.ntshare.blog.enums.ResponseCodeEnum;
import cn.ntshare.blog.exception.SystemException;
import cn.ntshare.blog.pojo.User;
import cn.ntshare.blog.service.ImgRecordService;
import cn.ntshare.blog.service.UserService;
import cn.ntshare.blog.util.CookieUtil;
import cn.ntshare.blog.util.JsonUtil;
import cn.ntshare.blog.util.MD5Util;
import cn.ntshare.blog.util.RedisUtil;
import cn.ntshare.blog.vo.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private FileClient fileClient;

    @Autowired
    private ImgRecordService imgRecordService;

    @Override
    public UserDTO checkLoginInfo(String account, String password) {
        password = MD5Util.MD5EncodeUtf8(password);
        UserDTO userDTO = userMapper.selectByAccountAndPassword(account, password);
        if(userDTO == null) {
            log.warn("账号：{} 登录失败", account);
            throw new SystemException(ResponseCodeEnum.LOGIN_FAILED);
        }
        return userDTO;
    }

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
    public Boolean updateInfo(HttpServletRequest request, User user) {
        Integer id = this.queryUserId(request);
        user.setId(id);
        int result = userMapper.updateInfo(user);
        if (result != 1) {
            throw new SystemException(ResponseCodeEnum.UPDATE_FAILED);
        }

        this.updateCache(id, request);

        return true;
    }

    @Override
    @Transactional
    public String updateAvatar(HttpServletRequest request, MultipartFile file) {
        Integer id = this.queryUserId(request);
        ServerResponse response = fileClient.upload(file);
        if (response.getCode() != 11) {
            throw new SystemException(ResponseCodeEnum.FILE_UPLOAD_FAILED);
        }
        String avatar = (String) response.getData();
        int result = userMapper.updateAvatar(id, avatar);
        if (result != 1) {
            throw new SystemException(ResponseCodeEnum.UPDATE_FAILED);
        }

        // 更新缓存
        this.updateCache(id, request);

        // 更新图片记录
        imgRecordService.updateImgByUserId(id, avatar);

        return avatar;
    }

    @Override
    public Boolean updatePassword(HttpServletRequest request, String oldPassword, String newPassword) {
        Integer id = this.queryUserId(request);
        // 确定旧密码
        int count = userMapper.queryByPassword(id, MD5Util.MD5EncodeUtf8(oldPassword));
        if (count != 1) {
            throw new SystemException(ResponseCodeEnum.WRONG_PASSWORD);
        }

        // 更新新密码
        String encodePassword = MD5Util.MD5EncodeUtf8(newPassword);
        int result = userMapper.updatePassword(id, encodePassword);

        if (result != 1) {
            throw new SystemException(ResponseCodeEnum.UPDATE_FAILED);
        }
        return true;
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

    @Override
    public Boolean updateCache(Integer id, HttpServletRequest request) {
        String token = CookieUtil.readCookie(request, SystemConstant.LOGIN_TOKEN);
        UserDTO userDTO = this.queryUserById(id);
        String userJson = JsonUtil.obj2String(userDTO);
        RedisUtil.setExpireTime(token, userJson, SystemConstant.REDIS_EXPIRE_TIME);
        log.info("用户ID：{}缓存信息更新成功", id);
        return true;
    }

}
