package cn.ntshare.blog.util;

import cn.ntshare.blog.enums.ResponseCodeEnum;
import cn.ntshare.blog.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
public class FtpUtil {

    private String host;
    private int port;
    private String username;
    private String password;
    private FTPClient FtpClient;

    private static final String ip = PropertiesUtil.getProperty("ftp.host");
    private static final int pt = Integer.parseInt(PropertiesUtil.getProperty("ftp.port", "21"));
    private static final String user = PropertiesUtil.getProperty("ftp.username");
    private static final String pwd = PropertiesUtil.getProperty("ftp.password");

    private FtpUtil() {
        this.host = ip;
        this.port = pt;
        this.username = user;
        this.password = pwd;
    }

    /**
     * 上传图片到Ftp服务器
     * @param file
     * @return
     */
    public static boolean uploadImg(File file) {
        return new FtpUtil().uploadFile("/images", file);
    }

    /**
     * 删除Ftp服务器上的单个图片
     * @param imgName
     * @return
     */
    public static boolean deleteImg(String imgName) {
        return new FtpUtil().deleteFile("/images", imgName);
    }

    /**
     * 删除多张图片
     * @param imgNames
     */
    public static void deleteImgs(List<String> imgNames) {
        new FtpUtil().deleteFiles("/images", imgNames);
    }

    /**
     * 上传文件到Ftp服务器
     * @param path
     * @param file
     * @return
     */
    private boolean uploadFile(String path, File file){
        try {
            if(connect()) {
                FileInputStream fileInputStream;
                FtpClient.changeWorkingDirectory(path);
                FtpClient.setBufferSize(2048);
                FtpClient.setControlEncoding("UTF-8");
                FtpClient.setFileType(FtpClient.BINARY_FILE_TYPE);      //设置文件格式为二进制格式
                FtpClient.enterLocalPassiveMode();
                fileInputStream = new FileInputStream(file);
                FtpClient.storeFile(file.getName(), fileInputStream);
                FtpClient.disconnect();
                fileInputStream.close();
                log.info("文件 {} 上传成功", file.getName());
                return true;
            }
        } catch (IOException e) {
            log.error("文件上传失败，{}", e.getMessage());
            throw new SystemException(ResponseCodeEnum.FILE_UPLOAD_FAILED);
        }
        return false;
    }

    /**
     * 删除Ftp服务器中的文件
     * @param path
     * @param fileName
     * @return
     */
    private boolean deleteFile(String path, String fileName){
        boolean uploadResult = false;
        try {
            if(connect()) {
                FtpClient.changeWorkingDirectory(path);
                if (FtpClient.deleteFile(fileName)) {
                    FtpClient.disconnect();
                    log.info("文件 {} 删除成功", fileName);
                    uploadResult = true;
                } else {
                    log.info("文件 {} 删除失败", fileName);
                    uploadResult = false;
                }
                FtpClient.disconnect();
            }
        } catch (IOException e) {
            log.error("文件删除失败, {}", e.getMessage());
            throw new SystemException(ResponseCodeEnum.FILE_DELETE_FAILED);
        }
        return uploadResult;
    }

    /**
     * 删除多个文件
     * @param path
     * @param imgNames
     */
    private int deleteFiles(String path, List<String> imgNames) {
        int count = 0;
        try {
            if (connect()) {
                FtpClient.changeWorkingDirectory(path);
                for (String s : imgNames) {
                    if (FtpClient.deleteFile(s)) {
                        log.info("文件 {} 删除成功", s);
                        count++;
                    } else {
                        log.info("文件 {} 删除失败", s);
                    }
                }
                log.info("共删除 {} 张图片", count);
                FtpClient.disconnect();
            }
        } catch (IOException e) {
            log.error("文件删除失败, {}", e.getMessage());
            throw new SystemException(ResponseCodeEnum.FILE_DELETE_FAILED);
        }
        return count;
    }


    /**
     * 连接到Ftp服务器
     *
     * @return
     */
    private boolean connect() {
        boolean connectResult;
        try {
            FtpClient = new FTPClient();
            FtpClient.connect(host, port);
            connectResult = FtpClient.login(username, password);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new SystemException(ResponseCodeEnum.CONN_FTP_FAILED);
        }
        return connectResult;
    }
}
