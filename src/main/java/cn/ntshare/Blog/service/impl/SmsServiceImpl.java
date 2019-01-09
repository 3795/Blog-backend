package cn.ntshare.Blog.service.impl;

import cn.ntshare.Blog.constant.SystemConstant;
import cn.ntshare.Blog.enums.ResponseCodeEnum;
import cn.ntshare.Blog.exception.SystemException;
import cn.ntshare.Blog.service.SmsService;
import cn.ntshare.Blog.util.CookieUtil;
import cn.ntshare.Blog.util.RandomUtil;
import cn.ntshare.Blog.util.RedisPoolUtil;
import cn.ntshare.Blog.util.SmsUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * Created By Seven.wk
 * Description: 短信服务实现
 * Created At 2019/01/09
 */
@Service
public class SmsServiceImpl implements SmsService {

    @Override
    public Boolean sendCaptchaSms(HttpServletResponse response, String phoneNumber, String captchaCode) {

        String key = RandomUtil.getUniqueKey();
        // 写入Redis
        RedisPoolUtil.setExpireTime(key, captchaCode, SystemConstant.SMS_EXPIRE_TIME);
        // 写入Cookie
        CookieUtil.writeCookie(response, SystemConstant.SMS_TOKEN, key);

        // 发送短信
        if (!SmsUtil.sendCaptchaSms(phoneNumber, captchaCode)) {
            throw new SystemException(ResponseCodeEnum.SMS_SEND_FAILED);
        }

        return true;
    }
}
