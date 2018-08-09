package com.seven.Blog.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created By Seven.wk
 * Description: 文件名称工具类
 * Created At 2018/08/08
 */
public class FileUtil {

    private static final String sysPath = "./src/main/resources/static/images";

    /**
     * 获得文件上传后的名称
     * @param originalFilename
     * @return
     */
    public static String getImgName(String originalFilename) {
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        String fileName = String.valueOf(System.currentTimeMillis()).substring(0, 11);
        return "/" + fileName + suffix;
    }

    public static String uploadImg(MultipartFile file) throws IOException {
        String imgPath = null;
        String imgName = getImgName(file.getOriginalFilename());
        File destination = new File(sysPath + imgName);
        FileUtils.copyInputStreamToFile(file.getInputStream(), destination);
        if(FTPUtil.uploadFile(destination)) {
            imgPath = "http://img.develop.com" + imgName;
            destination.delete();
        }
        return imgPath;
    }

}
