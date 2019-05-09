package cn.ntshare.blog.util;

import cn.ntshare.blog.enums.ResponseCodeEnum;
import cn.ntshare.blog.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Objects;

@Slf4j
public class FileUtil {
    private static final String sysPath = "./blog-file/src/main/resources/static/images";

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
        String imgName = getImgName(Objects.requireNonNull(file.getOriginalFilename()));
        File destination = new File(sysPath + imgName);
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), destination);
            if (FtpUtil.uploadImg(destination)) {
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
     * 删除FTP服务器中的单个图片
     * @param imgName
     * @return
     */
    public static Boolean deleteImg(String imgName) {
        return FtpUtil.deleteImg(imgName);
    }

    /**
     * 删除多个图片
     * @param imgNames
     */
    public static void deleteImgs(List<String> imgNames) {
        if (imgNames.size() == 0) {
            return;
        }
        FtpUtil.deleteImgs(imgNames);
    }
}
