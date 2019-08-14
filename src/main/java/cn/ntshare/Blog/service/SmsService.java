package cn.ntshare.Blog.service;

/**
 * Created By Seven.wk
 * Description: 短信服务
 * Created At 2019/01/09
 */
public interface SmsService {

    /**
     * 发送短信
     * @param smsType
     * @param phoneNumber
     * @param content
     * @return
     */
    Boolean sendSms(Integer smsType, String phoneNumber, String content);

}
