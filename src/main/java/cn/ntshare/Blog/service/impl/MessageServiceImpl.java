package cn.ntshare.Blog.service.impl;

import cn.ntshare.Blog.dao.MessageMapper;
import cn.ntshare.Blog.enums.ResponseCodeEnum;
import cn.ntshare.Blog.exception.SystemException;
import cn.ntshare.Blog.pojo.Message;
import cn.ntshare.Blog.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 实现层
 * Created At 2019/01/11
 */
@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public List<Message> queryByStatus(Integer status) {
        return messageMapper.queryByStatus(status);
    }

    @Override
    public void insert(String title, String content) {
        int result = messageMapper.insert(new Message(title, content));
        if (result != 1) {
            log.error("insert message error");
            throw new SystemException(ResponseCodeEnum.INSERT_FAILED);
        }
    }

    @Override
    public void updateStatus(Integer id) {
        int result = messageMapper.updateStatus(id);
        if (result != 1) {
            log.error("update message error, id = {}", id);
            throw new SystemException(ResponseCodeEnum.UPDATE_FAILED);
        }
    }

    @Override
    public void delete(Integer id) {
        int result = messageMapper.delete(id);
        if (result != 1) {
            log.error("delete message error, id = {}", id);
            throw new SystemException(ResponseCodeEnum.DELETE_FAILED);
        }
    }

    @Override
    public Integer countByStatus(Integer status) {
        return messageMapper.countByStatus(status);
    }

    @Override
    public void empty() {
        messageMapper.empty();
    }
}
