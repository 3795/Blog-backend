package com.seven.Blog.controller.api.v1.backend;

import com.seven.Blog.constant.SystemConstant;
import com.seven.Blog.dto.UserDTO;
import com.seven.Blog.enums.ResponseCodeEnum;
import com.seven.Blog.pojo.User;
import com.seven.Blog.service.UserService;
import com.seven.Blog.util.CookieUtil;
import com.seven.Blog.util.JsonUtil;
import com.seven.Blog.util.RedisPoolUtil;
import com.seven.Blog.vo.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        UserDTO userDTO = null;
        String key = CookieUtil.readCookie(request, SystemConstant.LOGIN_TOKEN);
        String userJson = RedisPoolUtil.get(key);
        User user = JsonUtil.string2Obj(userJson, User.class);
        if (user != null) {
            userDTO = new UserDTO(user.getUsername(), user.getAvatar());
        }
        return ServerResponse.success(userDTO);
    }

    /**
     * 获取用户的详细信息
     * @param request
     * @return
     */
    @GetMapping("/detail")
    public ServerResponse getDetailInfo(HttpServletRequest request) {
        UserDTO userDTO = new UserDTO();
        String key = CookieUtil.readCookie(request, SystemConstant.LOGIN_TOKEN);
        String userJson = RedisPoolUtil.get(key);
        User user = JsonUtil.string2Obj(userJson, User.class);
        if (user != null) {
            BeanUtils.copyProperties(user, userDTO);
            userDTO.setId(null);
        }
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
     * 更新用户基本信息
     * @param id
     * @param username
     * @param avatar
     * @return
     */
    @PutMapping("/info")
    public ServerResponse updateInfo(@RequestParam("id") Integer id,
                                     @RequestParam(value = "username", defaultValue = "") String username,
                                     @RequestParam(value = "avatar", defaultValue = "") String avatar) {
        User user = new User(id, username, avatar);
        userService.updateInfo(user);
        return ServerResponse.success(ResponseCodeEnum.UPDATE_SUCCESS);
    }
}
