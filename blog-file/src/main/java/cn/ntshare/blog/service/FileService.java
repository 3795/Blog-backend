package cn.ntshare.blog.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    void deleteImgs(List<String> imgList);
}
