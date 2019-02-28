package cn.ntshare.Blog.controller.api.v1.util;

import cn.ntshare.Blog.bo.CaptchaBO;
import cn.ntshare.Blog.constant.SystemConstant;
import cn.ntshare.Blog.enums.ResponseCodeEnum;
import cn.ntshare.Blog.service.SmsService;
import cn.ntshare.Blog.util.CaptchaUtil;
import cn.ntshare.Blog.util.RandomUtil;
import cn.ntshare.Blog.util.RedisUtil;
import cn.ntshare.Blog.vo.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created By Seven.wk
 * Description: 工具类API
 * Created At 2018/11/10
 */
@RestController
@RequestMapping
@Slf4j
@Api(tags = "工具接口")
public class UtilControllerV1 {

    @Autowired
    private SmsService smsService;

    @GetMapping("/captcha")
    @ApiOperation("图片验证码接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "length", value = "长度", paramType = "query"),
            @ApiImplicitParam(name = "width", value = "宽度", paramType = "query")
    })
    public void captcha(@RequestParam(value = "length", defaultValue = "80") Integer length,
                        @RequestParam(value = "width", defaultValue = "35") Integer width,
                        HttpSession httpSession,
                        HttpServletResponse response) throws IOException {
        CaptchaBO captcha = CaptchaUtil.createCaptcha(length, width);
        httpSession.setAttribute(SystemConstant.CAPTCHA_CODE, captcha.getCaptchaCode());
        ImageIO.write(captcha.getCaptchaImage(), "JPEG", response.getOutputStream());
    }

    @PostMapping("/sms")
    @ApiOperation("短信验证码接口")
    @ApiImplicitParam(name = "phoneToken", value = "手机号码Token", required = true, paramType = "query")
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
