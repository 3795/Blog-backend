package com.seven.Blog.controller.api.v1.util;

import com.seven.Blog.bo.CaptchaBO;
import com.seven.Blog.constant.SystemConstant;
import com.seven.Blog.util.CaptchaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
public class UtilControllerV1 {

    @GetMapping("/captcha")
    public void captcha(@RequestParam(value = "width", defaultValue = "80") Integer width,
                        @RequestParam(value = "height", defaultValue = "35") Integer height,
                        HttpSession httpSession,
                        HttpServletResponse response) throws IOException {
        CaptchaBO captcha = CaptchaUtil.createCaptcha(width, height);
        httpSession.setAttribute(SystemConstant.CAPTCHA_CODE, captcha.getCaptchaCode());
        ImageIO.write(captcha.getCaptchaImage(), "JPEG", response.getOutputStream());

    }
}
