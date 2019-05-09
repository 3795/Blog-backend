package cn.ntshare.blog.service.impl;

import cn.ntshare.blog.dao.ImgRecordMapper;
import cn.ntshare.blog.enums.ResponseCodeEnum;
import cn.ntshare.blog.exception.SystemException;
import cn.ntshare.blog.service.ImgRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ImgRecordServiceImpl implements ImgRecordService {

    @Autowired
    private ImgRecordMapper imgRecordMapper;

    @Override
    public Boolean insert(String img) {
        int result = imgRecordMapper.insert(img);
        if (result != 1) {
            log.warn("insert img_record error!");
            throw new SystemException(ResponseCodeEnum.INSERT_FAILED);
        }
        return true;
    }
}
