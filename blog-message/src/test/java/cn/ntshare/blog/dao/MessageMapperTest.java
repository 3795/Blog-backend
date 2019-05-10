package cn.ntshare.blog.dao;

import cn.ntshare.blog.pojo.Message;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MessageMapperTest {

    @Autowired
    private MessageMapper messageMapper;

    @Test
    public void test() {
        Message message = new Message("测试", "测试Mapper", new Date());
        int result = messageMapper.insert(message);
        Assert.assertEquals(result, 1);
    }

}