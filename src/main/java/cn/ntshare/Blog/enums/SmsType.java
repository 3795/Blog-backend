package cn.ntshare.Blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SmsType {
    captcha(1, "验证码短信"),
    ;
    Integer type;

    String desc;
}
