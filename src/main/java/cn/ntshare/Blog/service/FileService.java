package cn.ntshare.Blog.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created By Seven.wk
 * Description: 文件管理服务
 * Created At 2018/12/26
 */
public interface FileService {

    /**
     * 上传图片
     * @param file
     * @return
     */
    String uploadImg(MultipartFile file);

    /**
     * 删除图片
     * @param imgName
     * @return
     */
    Boolean deleteImg(String imgName);

}
