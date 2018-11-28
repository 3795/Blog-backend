package com.seven.Blog.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;

/**
 * Created By Seven.wk
 * Description: 验证码实体
 * Created At 2018/11/10
 */

@Setter
@Getter
@AllArgsConstructor
public class CaptchaBO {

    // 验证码图片
    private BufferedImage captchaImage;

    // 验证码数字
    private String captchaCode;

}
