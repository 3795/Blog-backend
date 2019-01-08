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

    private FTPUtil(String host, int port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    /**
     * 上传文件到FTP服务器
     * @param file
     * @return
     * @throws IOException
     */
    public static boolean uploadImg(File file) {
        FTPUtil ftpUtil = new FTPUtil(ip, pt, user, pwd);
        return ftpUtil.uploadFile("/images", file);
    }

    /**
     * 删除FTP服务器上的单个文件
     * @param imgName
     * @return
     */
    public static boolean deleteImg(String imgName) {
        FTPUtil ftpUtil = new FTPUtil(ip, pt, user, pwd);
        return ftpUtil.deleteFile("/images", imgName);
    }

    /**
     * 删除多个文件
     * @param imgNames
     */
    public static void deleteImgs(List<String> imgNames) {
        FTPUtil ftpUtil = new FTPUtil(ip, pt, user, pwd);
        ftpUtil.deleteFiles("/images", imgNames);
    }

    /**
     * 上传文件到FTP服务器
     * @param path
     * @param file
     * @return
     * @throws IOException
     */
    private boolean uploadFile(String path, File file){
        FileInputStream fileInputStream;
        try {
            if(connectServer(this.host, this.port, this.username, this.password)) {
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
            throw new SystemException(ResponseCodeEnum.CONN_FTP_FAILED);
        }
        throw new SystemException(ResponseCodeEnum.CONN_FTP_FAILED);
    }

    /**
     * 删除FTP服务器中的文件
     * @param path
     * @param fileName
     * @return
     * @throws IOException
     */
    private boolean deleteFile(String path, String fileName){
        try {
            if(connectServer(this.host, this.port, this.username, this.password)) {
                ftpClient.changeWorkingDirectory(path);
                if (ftpClient.deleteFile(fileName)) {
                    ftpClient.disconnect();
                    log.info("文件 {} 删除成功", fileName);
                    return true;
                } else {
                    ftpClient.disconnect();
                    log.info("文件 {} 删除失败", fileName);
                    return false;
                }
            }
        } catch (IOException e) {
            throw new SystemException(ResponseCodeEnum.CONN_FTP_FAILED);
        }
        throw new SystemException(ResponseCodeEnum.CONN_FTP_FAILED);
    }

    /**
     * 删除多个文件
     * @param path
     * @param imgNames
     */
    private void deleteFiles(String path, List<String> imgNames) {
        try {
            if(connectServer(this.host, this.port, this.username, this.password)) {
                int count = 0;
                ftpClient.changeWorkingDirectory(path);
                for (String s : imgNames) {
                    if (ftpClient.deleteFile(s)) {
                        log.info("文件 {} 删除成功", s);
                        count ++;
                    } else {
                        log.info("文件 {} 删除失败", s);
                    }
                }
                log.info("共删除 {} 张图片", count);
                ftpClient.disconnect();
            }
        } catch (IOException e) {
            throw new SystemException(ResponseCodeEnum.CONN_FTP_FAILED);
        }
    }


    /**
     * 连接到FTP服务器
     * @param ip
     * @param port
     * @param username
     * @param password
     * @return
     * @throws IOException
     */
    private boolean connectServer(String ip, int port, String username, String password) throws IOException {
        ftpClient = new FTPClient();
        ftpClient.connect(ip, port);
        return ftpClient.login(username, password);
    }
}
