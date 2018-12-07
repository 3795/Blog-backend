package com.seven.Blog.controller.api.v1.backend;

import com.seven.Blog.Exception.SystemException;
import com.seven.Blog.constant.SystemConstant;
import com.seven.Blog.enums.ResponseCodeEnum;
import com.seven.Blog.pojo.User;
import com.seven.Blog.service.UserService;
import com.seven.Blog.util.CookieUtil;
import com.seven.Blog.util.JsonUtil;
import com.seven.Blog.util.RedisPoolUtil;
import com.seven.Blog.vo.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created By Seven.wk
 * Description: 管理系统登录接口V1版
 * Created At 2018/11/10
 */
@RestController
@RequestMapping("/backend")
@Slf4j
public class BLoginControllerV1 {

    @Autowired
    private UserService userService;

    /**
     * 用户登录接口
     * @param account
     * @param password
     * @param captchaCode
     * @param session
     * @return
     */
    @PostMapping("/login")
    public ServerResponse login(@RequestParam("account") String account,
                                @RequestParam("password") String password,
                                @RequestParam("captchaCode") String captchaCode,
                                HttpSession session,
                                HttpServletResponse response) {

        if(session.getAttribute(SystemConstant.CAPTCHA_CODE) == null ||
                !session.getAttribute(SystemConstant.CAPTCHA_CODE).equals(captchaCode))
            throw new SystemException(ResponseCodeEnum.VERIFICATION_CODE_ERROR);

        User user = userService.checkLoginInfo(account, password);

        CookieUtil.writeCookie(response, SystemConstant.LOGIN_TOKEN, session.getId());

        String userJson = JsonUtil.obj2String(user);
        RedisPoolUtil.setExpireTime(session.getId(), userJson, SystemConstant.REDIS_EXPIRE_TIME);

        log.info("用户ID：{} 登录成功", user.getId());
        return ServerResponse.success(ResponseCodeEnum.LOGIN_SUCCESS);
    }
}
