package cn.ntshare.Blog.controller.api.v1.backend;

import cn.ntshare.Blog.constant.SystemConstant;
import cn.ntshare.Blog.dto.UserDTO;
import cn.ntshare.Blog.enums.ResponseCodeEnum;
import cn.ntshare.Blog.service.IpRecordService;
import cn.ntshare.Blog.service.SmsService;
import cn.ntshare.Blog.service.UserService;
import cn.ntshare.Blog.util.*;
import cn.ntshare.Blog.vo.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
 * Description: 登录接口
 * Created At 2018/11/10
 */
@RestController
@RequestMapping("/backend")
@Slf4j
@Api(tags = "登录接口")
public class BLoginControllerV1 {

    @Autowired
    private UserService userService;

    @Autowired
    private IpRecordService ipRecordService;

    @Autowired
    private SmsService smsService;

    /**
     * 用户登录接口
     * @param account
     * @param password
     * @param captchaCode
     * @param session
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("用户登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "账号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "captchaCode", value = "验证码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "phoneCaptchaCode", value = "短信验证码", paramType = "query")
    })
    public ServerResponse login(@RequestParam("account") String account,
                                @RequestParam("password") String password,
                                @RequestParam("captchaCode") String captchaCode,
                                @RequestParam(value = "phoneCaptchaCode", required = false) String phoneCaptchaCode,
                                HttpSession session,
                                HttpServletRequest request,
                                HttpServletResponse response) {

        // 校验图片验证码
        if(session.getAttribute(SystemConstant.CAPTCHA_CODE) == null ||
                !session.getAttribute(SystemConstant.CAPTCHA_CODE).equals(captchaCode)) {
            return ServerResponse.error(ResponseCodeEnum.VERIFICATION_CODE_ERROR);
        }

        // 验证账号和密码
        UserDTO user = userService.checkLoginInfo(account, password);

        String ip = IPAddressUtil.getIpAdrress(request);
        // 第一次请求
        if (phoneCaptchaCode == null) {
            // 如果没有记录
            if (!ipRecordService.isExists(ip)) {
                String phoneToken = RandomUtil.getUniqueKey();
                RedisUtil.setExpireTime(phoneToken, user.getPhone(), 10 * SystemConstant.MINUTE);
                String phone = user.getPhone().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
                String[] data = {phone, phoneToken};
                return ServerResponse.success(ResponseCodeEnum.IP_NOT_VERIFIED, data);
            } else {
                // 有记录就增加一次访问量
                ipRecordService.increaseCount(ip);
            }
        } else {
            // 验证短信验证码的正确性
            smsService.verifyCaptchaCode(request, phoneCaptchaCode);
            // 添加一条IP记录
            ipRecordService.insert(ip);
        }

        // 将Token令牌写入Cookie
        String key = RandomUtil.getUniqueKey();
        CookieUtil.writeCookie(response, SystemConstant.LOGIN_TOKEN, key);

        // 维护用户登录状态队列
        String loginListKey = SystemConstant.LOGIN_LIST_PREFIX + user.getId();
        Long len = RedisUtil.llen(loginListKey);
        if (len >= SystemConstant.ALLOW_LOGIN_NUMBER) {
            // 踢出最先登录的用户
            String oldKey = RedisUtil.lpop(loginListKey);
            RedisUtil.del(oldKey);
        }

        // 加入最新登录用户的信息，重置队列过期时间
        RedisUtil.rpush(loginListKey, key);
        RedisUtil.expire(loginListKey, 2 * SystemConstant.LOGIN_EXPIRE_TIME);

        // 将用户信息写入Redis
        String userJson = JsonUtil.obj2String(user);
        RedisUtil.setExpireTime(key, userJson, SystemConstant.LOGIN_EXPIRE_TIME);

        log.info("用户：{} 登录成功", user.getUsername());
        return ServerResponse.success(ResponseCodeEnum.LOGIN_SUCCESS);
    }

}
