package cn.ntshare.blog.service;

/**
 * Created By Seven.wk
 * Description: 邮件服务
 * Created At 2019/01/08
 */
public interface MailService {

    /**
     * 发送邮件
     * @param to        收件人邮箱
     * @param subject       邮件主题
     * @param text      邮件内容
     */
    Boolean sendMail(String to, String subject, String text);
}
