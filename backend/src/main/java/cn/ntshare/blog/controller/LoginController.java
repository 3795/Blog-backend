package cn.ntshare.blog.controller;

import cn.ntshare.blog.bo.CaptchaBO;
import cn.ntshare.blog.constant.SystemConstant;
import cn.ntshare.blog.dto.UserDTO;
import cn.ntshare.blog.enums.ResponseCodeEnum;
import cn.ntshare.blog.service.IpRecordService;
import cn.ntshare.blog.service.SmsService;
import cn.ntshare.blog.service.UserService;
import cn.ntshare.blog.util.*;
import cn.ntshare.blog.vo.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping
@Slf4j
public class LoginController {

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

        // 写入Cookie
        String key = RandomUtil.getUniqueKey();
        CookieUtil.writeCookie(response, SystemConstant.LOGIN_TOKEN, key);

        // 写入Redis
        String userJson = JsonUtil.obj2String(user);
        RedisUtil.setExpireTime(key, userJson, SystemConstant.REDIS_EXPIRE_TIME);

        log.info("用户：{} 登录成功", user.getUsername());
        return ServerResponse.success(ResponseCodeEnum.LOGIN_SUCCESS);
    }

    @GetMapping("/captcha")
    public void captcha(@RequestParam(value = "length", defaultValue = "80") Integer length,
                        @RequestParam(value = "width", defaultValue = "35") Integer width,
                        HttpSession httpSession,
                        HttpServletResponse response) throws IOException {
        CaptchaBO captcha = CaptchaUtil.createCaptcha(length, width);
        httpSession.setAttribute(SystemConstant.CAPTCHA_CODE, captcha.getCaptchaCode());
        ImageIO.write(captcha.getCaptchaImage(), "JPEG", response.getOutputStream());
    }

    @PostMapping("/sms")
    public ServerResponse sendSms(HttpServletResponse response,
                                  @RequestParam("phoneToken") String phoneToken) {
        String phone = RedisUtil.get(phoneToken);
        if (phone == null) {
            return ServerResponse.error(ResponseCodeEnum.INVALID_TOKEN);
        }

        // 进行防刷验证
        String value = RedisUtil.get(phone);
        if (value == null) {
            String code = RandomUtil.getRandomNumber(6);
            smsService.sendCaptchaSms(response, phone, code);
            return ServerResponse.success(ResponseCodeEnum.SMS_SEND_SUCCESS);
        } else {
            int count = Integer.parseInt(value) + 1;
            if (count < 6) {
                RedisUtil.setExpireTime(phone, String.valueOf(count), SystemConstant.MINUTE);
            } else {
                log.warn("短信接口被异常请求，请求号码：{}，请求次数：{}", phone, count);
                RedisUtil.setExpireTime(phone, String.valueOf(count), 5 * SystemConstant.MINUTE);
            }
            return ServerResponse.error(ResponseCodeEnum.REQUEST_TOO_FREQUENTLY);
        }
    }
}
