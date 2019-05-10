package cn.ntshare.blog.service.impl;

import cn.ntshare.blog.enums.ResponseCodeEnum;
import cn.ntshare.blog.exception.SystemException;
import cn.ntshare.blog.service.SmsService;
import cn.ntshare.blog.util.SmsUtil;
import org.springframework.stereotype.Service;

/**
 * Created By Seven.wk
 * Description: 短信服务实现
 * Created At 2019/01/09
 */
@Service
public class SmsServiceImpl implements SmsService {

    @Override
    public Boolean sendCaptchaSms(String phoneNumber, String captchaCode) {

        // 发送短信
        if (!SmsUtil.sendCaptchaSms(phoneNumber, captchaCode)) {
            throw new SystemException(ResponseCodeEnum.SMS_SEND_FAILED);
        }

        return true;
    }

}
