package com.seven.Blog.service.impl;

import com.seven.Blog.Exception.SystemException;
import com.seven.Blog.enums.ResponseCodeEnum;
import com.seven.Blog.service.FileService;
import com.seven.Blog.util.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created By Seven.wk
 * Description: 文件管理服务
 * Created At 2018/12/26
 */
@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImg(MultipartFile file) {
        try {
            if(!file.isEmpty()) {
                return FileUtil.uploadImg(file);
            } else {
                throw new SystemException(ResponseCodeEnum.FILE_CANNOT_BE_EMPTY);
            }
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
