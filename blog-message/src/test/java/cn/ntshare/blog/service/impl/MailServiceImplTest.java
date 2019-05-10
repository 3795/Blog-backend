package cn.ntshare.blog.service.impl;

import cn.ntshare.blog.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MailServiceImplTest {

    @Autowired
    private MailService mailService;

    @Test
    public void sendMail() {
        mailService.sendMail("seven.io@qq.com", "测试邮件", "测试邮件功能是否正常");
    }
}