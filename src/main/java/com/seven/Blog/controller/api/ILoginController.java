package com.seven.Blog.controller.api;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.seven.Blog.enums.ResponseCodeEnum;
import com.seven.Blog.response.ServerResponse;
import com.seven.Blog.service.UserService;
import com.seven.Blog.utils.Const;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created By Seven.wk
 * Description: 用户信息API接口
 * Created At 2018/08/06
 */
@RestController
@RequestMapping("/api")
public class ILoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private DefaultKaptcha defaultKaptcha;

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

    @GetMapping("/captcha")
    public void captcha(HttpServletResponse response,
                        HttpSession session) throws IOException {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            session.setAttribute(Const.CAPTCHA_CODE, createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

}
