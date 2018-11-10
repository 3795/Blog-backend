package com.seven.Blog.controller.api.v1.manage;

import com.seven.Blog.Exception.SystemException;
import com.seven.Blog.constant.SystemConstant;
import com.seven.Blog.enums.ResponseCodeEnum;
import com.seven.Blog.service.UserService;
import com.seven.Blog.util.ConstUtil;
import com.seven.Blog.vo.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created By Seven.wk
 * Description: 管理系统登录接口V1版
 * Created At 2018/11/10
 */
@RestController
@RequestMapping("/blog/v1")
@Slf4j
public class MLoginControllerV1 {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ServerResponse login(@RequestParam("account") String account,
                                @RequestParam("password") String password,
                                @RequestParam("captchaCode") String captchaCode,
                                HttpSession session) {
        if(!session.getAttribute(SystemConstant.CAPTCHA_CODE).equals(captchaCode))
            throw new SystemException(ResponseCodeEnum.VERIFICATION_CODE_ERROR);

        // 验证成功后移除验证码
        session.removeAttribute(SystemConstant.CAPTCHA_CODE);

        ServerResponse serverResponse = userService.checkLoginInfo(account, password);
        Integer id = (Integer) serverResponse.getData();
        session.setAttribute(ConstUtil.USER_ID, id);
        log.info("用户ID：{} 登录成功", id);
        return serverResponse;
    }
}
