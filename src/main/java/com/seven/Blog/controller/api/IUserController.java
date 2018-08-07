package com.seven.Blog.controller.api;

import com.seven.Blog.enums.ResponseCodeEnum;
import com.seven.Blog.response.ServerResponse;
import com.seven.Blog.service.UserService;
import com.seven.Blog.utils.Const;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created By Seven.wk
 * Description: 用户信息API接口
 * Created At 2018/08/06
 */
@RestController
@RequestMapping("/api/user")
public class IUserController {

    @Autowired
    private UserService userService;

    @PostMapping("/doLogin")
    public ServerResponse doLogin(@Param("account") String account,
                                  @Param("password") String password,
                                  @Param("captchaCode") String captchaCode,
                                  HttpSession session) {
        if(!session.getAttribute(Const.CAPTCHA_CODE).equals(captchaCode))
            return ServerResponse.error(ResponseCodeEnum.VERIFICATION_CODE_ERROR);
        ServerResponse serverResponse = userService.checkLoginInfo(account, password);
        if (serverResponse.getCode() == ResponseCodeEnum.LOGIN_SUCCESS.getCode()) {
            Integer id = (Integer) serverResponse.getData();
            session.setAttribute(Const.USER_ID, id);
        }
        return userService.checkLoginInfo(account, password);
    }

}
