package com.seven.Blog.controller.api.v1.manage;

import com.seven.Blog.constant.SystemConstant;
import com.seven.Blog.enums.ResponseCodeEnum;
import com.seven.Blog.util.CookieUtil;
import com.seven.Blog.util.RedisPoolUtil;
import com.seven.Blog.vo.ServerResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created By Seven.wk
 * Description: 用户控制器
 * Created At 2018/11/14
 */
@RestController
@RequestMapping("/blog/v1/user")
@CrossOrigin
public class MUserControllerV1 {

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
}
