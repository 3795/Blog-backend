package com.seven.Blog.utils;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created By Seven.wk
 * Description: FTP工具类
 * Created At 2018/08/09
 */
@Component
public class FTPUtil {

    @Autowired
    private PropertiesUtil propertiesUtil;

    private FTPClient ftpClient;

    public FTPUtil() { }

    /**
     * 上传文件到FTP服务器
     * @param file
     * @return
     * @throws IOException
     */
    public boolean uploadFile(File file) throws IOException {
        return uploadFile("images", file);
    }

    /**
     * 上传文件到FTP服务器
     * @param path
     * @param file
     * @return
     * @throws IOException
     */
    public boolean uploadFile(String path, File file) throws IOException {
        boolean isSuccess = false;
        FileInputStream fileInputStream = null;
        if(connectServer(propertiesUtil.getHost(), propertiesUtil.getPort(), propertiesUtil.getUsername(), propertiesUtil.getPassword())) {
            ftpClient.changeWorkingDirectory(path);
            ftpClient.setBufferSize(2048);
            ftpClient.setControlEncoding("UTF-8");
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);      //设置文件格式为二进制格式
            ftpClient.enterLocalPassiveMode();
            fileInputStream = new FileInputStream(file);
            ftpClient.storeFile(file.getName(), fileInputStream);
            ftpClient.disconnect();
            fileInputStream.close();
            isSuccess = true;
        }
        return isSuccess;
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
        boolean isSuccess = false;
        ftpClient = new FTPClient();
        ftpClient.connect(ip, port);
        isSuccess = ftpClient.login(username, password);
        return isSuccess;
    }

}
