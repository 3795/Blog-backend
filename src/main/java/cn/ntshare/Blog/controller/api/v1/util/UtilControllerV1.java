package cn.ntshare.Blog.controller.api.v1.util;

import cn.ntshare.Blog.bo.CaptchaBO;
import cn.ntshare.Blog.constant.SystemConstant;
import cn.ntshare.Blog.enums.ResponseCodeEnum;
import cn.ntshare.Blog.service.CaptchaCodeService;
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

import javax.annotation.Resource;
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

    @Resource(name = "captchaCodeService")
    private CaptchaCodeService captchaCodeService;

    @GetMapping("/captcha")
    @ApiOperation("图片验证码接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "length", value = "长度", paramType = "query"),
            @ApiImplicitParam(name = "height", value = "宽度", paramType = "query")
    })
    public void captcha(@RequestParam(value = "length", defaultValue = "80") Integer length,
                        @RequestParam(value = "height", defaultValue = "35") Integer height,
                        HttpSession httpSession,
                        HttpServletResponse response) throws IOException {
        CaptchaBO captcha = CaptchaUtil.createCaptcha(length, height);
        httpSession.setAttribute(SystemConstant.CAPTCHA_CODE, captcha.getCaptchaCode());
        response.setContentType("image/JPEG");
        ImageIO.write(captcha.getCaptchaImage(), "JPEG", response.getOutputStream());
    }

    @PostMapping("/sms")
    @ApiOperation("验证码接口，发送短信验证码或者邮件验证码")
    @ApiImplicitParam(name = "phoneToken", value = "手机号码Token或者邮件Token", required = true, paramType = "query")
    public ServerResponse sendSms(HttpServletResponse response,
                                  @RequestParam("phoneToken") String token) {
        String account = RedisUtil.get(token);
        if (account == null) {
            return ServerResponse.error(ResponseCodeEnum.INVALID_TOKEN);
        }

        // 进行防刷验证
        String value = RedisUtil.get(account);
        if (value == null) {
            String code = RandomUtil.getRandomNumber(6);
            captchaCodeService.sendCaptcha(response, account, code);
            return ServerResponse.success(ResponseCodeEnum.CAPTCHA_SEND_SUCCESS);
        } else {
            int count = Integer.parseInt(value) + 1;
            if (count < 6) {
                RedisUtil.setExpireTime(account, String.valueOf(count), SystemConstant.MINUTE);
            } else {
                log.warn("动态验证码接口被异常请求，请求号码：{}，请求次数：{}", account, count);
                RedisUtil.setExpireTime(account, String.valueOf(count), 5 * SystemConstant.MINUTE);
            }
            return ServerResponse.error(ResponseCodeEnum.REQUEST_TOO_FREQUENTLY);
        }
    }
}
