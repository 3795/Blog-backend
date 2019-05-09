package cn.ntshare.blog.service.impl;

import cn.ntshare.blog.enums.ResponseCodeEnum;
import cn.ntshare.blog.exception.SystemException;
import cn.ntshare.blog.service.FileService;
import cn.ntshare.blog.service.ImgRecordService;
import cn.ntshare.blog.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @Override
    public void deleteImgs(List<String> imgList) {
        FileUtil.deleteImgs(imgList);
    }
}
