package com.seven.Blog.controller.api.v1.backend;

import com.seven.Blog.constant.SystemConstant;
import com.seven.Blog.dto.UserDTO;
import com.seven.Blog.enums.ResponseCodeEnum;
import com.seven.Blog.form.UserForm;
import com.seven.Blog.pojo.User;
import com.seven.Blog.service.UserService;
import com.seven.Blog.util.CookieUtil;
import com.seven.Blog.util.JsonUtil;
import com.seven.Blog.util.RedisPoolUtil;
import com.seven.Blog.vo.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created By Seven.wk
 * Description: 用户控制器
 * Created At 2018/11/14
 */
@RestController
@RequestMapping("/backend/user")
@CrossOrigin
@Slf4j
public class BUserControllerV1 {

    @Autowired
    private UserService userService;

    /**
     * 获取用户的简要信息
     * @param request
     * @return
     */
    @GetMapping("/brief")
    public ServerResponse getBriefInfo(HttpServletRequest request) {
        UserDTO user = userService.queryUserInfo(request);
        UserDTO userDTO = new UserDTO(user.getUsername(), user.getAvatar());
        return ServerResponse.success(userDTO);
    }

    /**
     * 获取用户的详细信息
     * @param request
     * @return
     */
    @GetMapping("/detail")
    public ServerResponse getDetailInfo(HttpServletRequest request) {
        UserDTO userDTO = userService.queryUserInfo(request);
        userDTO.setId(null);
        return ServerResponse.success(userDTO);
    }

    /**
     * 确认用户登录
     * @return
     */
    @GetMapping("isLogin")
    public ServerResponse isLogin() {
        return ServerResponse.success(true);
    }

    /**
     * 用户退出登录
     * @return
     */
    @GetMapping("/logout")
    public ServerResponse logout(HttpServletRequest request,
                                 HttpServletResponse response) {
        String loginToken = CookieUtil.readCookie(request, SystemConstant.LOGIN_TOKEN);
        CookieUtil.delCookie(request, response, SystemConstant.LOGIN_TOKEN);
        RedisPoolUtil.del(loginToken);
        return ServerResponse.success(ResponseCodeEnum.LOGOUT_SUCCESS);
    }

    /**
     * 用户更新信息
     * @param request
     * @param userForm
     * @param result
     * @return
     */
    @PutMapping("/info")
    public ServerResponse updateInfo(HttpServletRequest request,
                                     @Valid UserForm userForm,
                                     BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.error(result.getFieldError().getDefaultMessage());
        }
        User user = new User();
        BeanUtils.copyProperties(userForm, user);
        userService.updateInfo(request, user);
        return ServerResponse.success(ResponseCodeEnum.UPDATE_SUCCESS);
    }

    /**
     * 更新用户头像
     * @param request
     * @param file
     * @return
     */
    @PatchMapping("/avatar")
    public ServerResponse updateAvatar(HttpServletRequest request,
                                       @RequestParam("file") MultipartFile file) {
        userService.updateAvatar(request, file);
        return ServerResponse.success(ResponseCodeEnum.UPDATE_SUCCESS);
    }

    /**
     * 更新用户密码
     * @param request
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @PatchMapping("/password")
    public ServerResponse updatePassword(HttpServletRequest request,
                                         @RequestParam("oldPassword") String oldPassword,
                                         @RequestParam("newPassword") String newPassword) {
        userService.updatePassword(request, oldPassword, newPassword);
        return ServerResponse.success(ResponseCodeEnum.UPDATE_SUCCESS);
    }
}
