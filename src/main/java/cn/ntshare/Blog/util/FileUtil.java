package cn.ntshare.Blog.util;

import cn.ntshare.Blog.Exception.SystemException;
import cn.ntshare.Blog.enums.ResponseCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created By Seven.wk
 * Description: 文件名称工具类
 * Created At 2018/08/08
 */
@Slf4j
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

    /**
     * 上传文件
     * @param file
     * @return
     */
    public static String uploadImg(MultipartFile file) {
        String imgName = getImgName(file.getOriginalFilename());
        File destination = new File(sysPath + imgName);
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), destination);
            if (FTPUtil.uploadImg(destination)) {
                return PropertiesUtil.getProperty("imgServerName") + imgName;
            } else {
                throw new SystemException(ResponseCodeEnum.FILE_UPLOAD_FAILED);
            }
        } catch(Exception e) {
            throw new SystemException(ResponseCodeEnum.FILE_UPLOAD_FAILED);
        } finally {
            destination.delete();
        }
    }

    /**
     * 删除FTP服务器上的图片
     * @param imgName
     * @return
     */
    public static Boolean deleteImg(String imgName) {
        return FTPUtil.deleteImg(imgName);
    }

}
