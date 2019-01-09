package cn.ntshare.Blog.util;

import cn.ntshare.Blog.constant.SystemConstant;
import cn.ntshare.Blog.enums.ResponseCodeEnum;
import cn.ntshare.Blog.exception.SystemException;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Created By Seven.wk
 * Description: 短信发送工具
 * Created At 2019/01/09
 */
@Slf4j
public class SmsUtil {

    // 读取配置属性
    private static final Integer appId = Integer.parseInt(PropertiesUtil.getProperty("sms.appId", "0"));
    private static final String appKey = PropertiesUtil.getProperty("sms.appKey", "");
    private static final Integer captchaTemplateId = Integer.parseInt(PropertiesUtil.getProperty("sms.captchaTemplateId", "0"));
    private static final String smsSign = PropertiesUtil.getProperty("sms.sign", "");

    public static boolean sendCaptchaSms(String phoneNumber, String captchaCode) {
        // 将秒数转换成分钟
        String time = String.valueOf(SystemConstant.SMS_EXPIRE_TIME / 60);
        String[] params = {captchaCode, time};
        return singleSender(phoneNumber, params, captchaTemplateId);
    }

    private static boolean singleSender(String phoneNumber, String[] params, Integer templateId) {
        SmsSingleSender sender = new SmsSingleSender(appId, appKey);
        SmsSingleSenderResult result;
        try {
            result = sender.sendWithParam("86", phoneNumber, templateId, params, smsSign, "", "");
        } catch (HTTPException | IOException e) {
            log.error("短信业务出错，错误信息：{}",e);
            throw new SystemException(ResponseCodeEnum.SMS_SEND_ERROR);
        }
        if (result == null || result.result != 0) {
            log.warn("短信发送失败，错误码为 {}", result.result);
            return false;
        }
        return true;
    }

}
