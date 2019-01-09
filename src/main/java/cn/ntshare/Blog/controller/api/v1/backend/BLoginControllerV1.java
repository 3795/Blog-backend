package cn.ntshare.Blog.controller.api.v1.backend;

import cn.ntshare.Blog.constant.SystemConstant;
import cn.ntshare.Blog.dto.UserDTO;
import cn.ntshare.Blog.enums.ResponseCodeEnum;
import cn.ntshare.Blog.exception.SystemException;
import cn.ntshare.Blog.service.UserService;
import cn.ntshare.Blog.util.*;
import cn.ntshare.Blog.vo.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
                                HttpServletRequest request,
                                HttpServletResponse response) {

        if(session.getAttribute(SystemConstant.CAPTCHA_CODE) == null ||
                !session.getAttribute(SystemConstant.CAPTCHA_CODE).equals(captchaCode)) {
            throw new SystemException(ResponseCodeEnum.VERIFICATION_CODE_ERROR);
        }

        // 验证账号和密码
        UserDTO user = userService.checkLoginInfo(account, password);

        // todo 验证IP地址
        String ip = IPAddressUtil.getIpAdrress(request);
        System.out.println(ip);

        // 写入Cookie
        String key = RandomUtil.getUniqueKey();
        CookieUtil.writeCookie(response, SystemConstant.LOGIN_TOKEN, key);

        // 写入Redis
        String userJson = JsonUtil.obj2String(user);
        RedisPoolUtil.setExpireTime(key, userJson, SystemConstant.REDIS_EXPIRE_TIME);

        log.info("用户：{} 登录成功", user.getUsername());
        return ServerResponse.success(ResponseCodeEnum.LOGIN_SUCCESS);
    }
}
