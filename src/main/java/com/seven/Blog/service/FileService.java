package com.seven.Blog.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created By Seven.wk
 * Description: 文件管理服务
 * Created At 2018/12/26
 */
public interface FileService {

    String uploadImg(MultipartFile file);

}
