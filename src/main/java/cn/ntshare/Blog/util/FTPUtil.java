package cn.ntshare.Blog.util;

import cn.ntshare.Blog.exception.SystemException;
import cn.ntshare.Blog.enums.ResponseCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created By Seven.wk
 * Description: FTP工具类
 * Created At 2018/08/09
 */
@Slf4j
public class FTPUtil {

    private String host;
    private int port;
    private String username;
    private String password;
    private FTPClient ftpClient;

    private static final String ip = PropertiesUtil.getProperty("ftp.host");
    private static final int pt = Integer.parseInt(PropertiesUtil.getProperty("ftp.port", "21"));
    private static final String user = PropertiesUtil.getProperty("ftp.username");
    private static final String pwd = PropertiesUtil.getProperty("ftp.password");

    private FTPUtil() {
        this.host = ip;
        this.port = pt;
        this.username = user;
        this.password = pwd;
    }

    /**
     * 上传图片到FTP服务器
     * @param file
     * @return
     */
    public static boolean uploadImg(File file) {
        return new FTPUtil().uploadFile("/images", file);
    }

    /**
     * 删除FTP服务器上的单个图片
     * @param imgName
     * @return
     */
    public static boolean deleteImg(String imgName) {
        return new FTPUtil().deleteFile("/images", imgName);
    }

    /**
     * 删除多张图片
     * @param imgNames
     */
    public static void deleteImgs(List<String> imgNames) {
        new FTPUtil().deleteFiles("/images", imgNames);
    }

    /**
     * 上传文件到FTP服务器
     * @param path
     * @param file
     * @return
     */
    private boolean uploadFile(String path, File file){
        try {
            if(connect()) {
                FileInputStream fileInputStream;
                ftpClient.changeWorkingDirectory(path);
                ftpClient.setBufferSize(2048);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);      //设置文件格式为二进制格式
                ftpClient.enterLocalPassiveMode();
                fileInputStream = new FileInputStream(file);
                ftpClient.storeFile(file.getName(), fileInputStream);
                ftpClient.disconnect();
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
     * 删除FTP服务器中的文件
     * @param path
     * @param fileName
     * @return
     */
    private boolean deleteFile(String path, String fileName){
        boolean uploadResult = false;
        try {
            if(connect()) {
                ftpClient.changeWorkingDirectory(path);
                if (ftpClient.deleteFile(fileName)) {
                    ftpClient.disconnect();
                    log.info("文件 {} 删除成功", fileName);
                    uploadResult = true;
                } else {
                    log.info("文件 {} 删除失败", fileName);
                    uploadResult = false;
                }
                ftpClient.disconnect();
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
                ftpClient.changeWorkingDirectory(path);
                for (String s : imgNames) {
                    if (ftpClient.deleteFile(s)) {
                        log.info("文件 {} 删除成功", s);
                        count++;
                    } else {
                        log.info("文件 {} 删除失败", s);
                    }
                }
                log.info("共删除 {} 张图片", count);
                ftpClient.disconnect();
            }
        } catch (IOException e) {
            log.error("文件删除失败, {}", e.getMessage());
            throw new SystemException(ResponseCodeEnum.FILE_DELETE_FAILED);
        }
        return count;
    }


    /**
     * 连接到FTP服务器
     *
     * @return
     */
    private boolean connect() {
        boolean connectResult;
        try {
            ftpClient = new FTPClient();
            ftpClient.connect(host, port);
            connectResult = ftpClient.login(username, password);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new SystemException(ResponseCodeEnum.CONN_FTP_FAILED);
        }
        return connectResult;
    }
}
