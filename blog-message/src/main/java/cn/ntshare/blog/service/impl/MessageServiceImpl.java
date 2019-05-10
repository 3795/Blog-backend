package cn.ntshare.blog.service.impl;

import cn.ntshare.blog.dao.MessageMapper;
import cn.ntshare.blog.enums.ResponseCodeEnum;
import cn.ntshare.blog.exception.SystemException;
import cn.ntshare.blog.pojo.Message;
import cn.ntshare.blog.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public void insert(String title, String content, Date time) {
        int result = messageMapper.insert(new Message(title, content, time));
        if (result != 1) {
            log.error("insert message error");
            throw new SystemException(ResponseCodeEnum.INSERT_FAILED);
        }
    }
}
