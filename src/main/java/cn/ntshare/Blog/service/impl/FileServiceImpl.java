package cn.ntshare.Blog.service.impl;

import cn.ntshare.Blog.Exception.SystemException;
import cn.ntshare.Blog.enums.ResponseCodeEnum;
import cn.ntshare.Blog.service.FileService;
import cn.ntshare.Blog.service.ImgRecordService;
import cn.ntshare.Blog.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created By Seven.wk
 * Description: 文件管理服务
 * Created At 2018/12/26
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private ImgRecordService imgRecordService;

    @Override
    public String uploadImg(MultipartFile file) {
        if (!file.isEmpty()) {
            String img =  FileUtil.uploadImg(file);

            // 添加图片记录
            imgRecordService.insert(img);

            return img;
        } else {
            throw new SystemException(ResponseCodeEnum.FILE_CANNOT_BE_EMPTY);
        }
    }

    @Override
    public Boolean deleteImg(String imgName) {
        if (FileUtil.deleteImg(imgName)) {
            return true;
        } else {
            throw new SystemException(ResponseCodeEnum.FILE_DELETE_FAILED);
        }
    }
}
