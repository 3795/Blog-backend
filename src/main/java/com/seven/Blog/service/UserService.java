package com.seven.Blog.service;

import com.seven.Blog.dto.UserDTO;
import com.seven.Blog.pojo.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created By Seven.wk
 * Description: 用户服务接口
 * Created At 2018/08/06
 */
public interface UserService {

    UserDTO checkLoginInfo(String account, String password);

    UserDTO queryUserById(Integer id);

    UserDTO queryUserInfo(HttpServletRequest request);

    Boolean updateInfo(HttpServletRequest request, User user);

    String updateAvatar(HttpServletRequest request, MultipartFile file);

    Boolean updatePassword(HttpServletRequest request, String oldPassword, String newPassword);

    Integer queryUserId(HttpServletRequest request);

    Boolean updateCache(Integer id, HttpServletRequest request);
}
