package com.seven.Blog.utils;

/**
 * Created By Seven.wk
 * Description: 文件名称工具类
 * Created At 2018/08/08
 */
public class FileNameUtil {

    public static String getFileName(String originalFilename) {
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        String fileName = String.valueOf(System.currentTimeMillis()).substring(0, 11);
        return "/images/" + fileName + suffix;
    }

}
