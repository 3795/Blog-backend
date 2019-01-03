package cn.ntshare.Blog.util;

import cn.ntshare.Blog.Exception.SystemException;
import cn.ntshare.Blog.enums.ResponseCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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

    public FTPUtil(String host,int port,String username,String password) {
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
    public static boolean uploadFile(File file) throws IOException {
        FTPUtil ftpUtil = new FTPUtil(ip, pt, user, pwd);
        return ftpUtil.uploadFile("images", file);
    }

    /**
     * 上传文件到FTP服务器
     * @param path
     * @param file
     * @return
     * @throws IOException
     */
    public boolean uploadFile(String path, File file) throws IOException {
        FileInputStream fileInputStream = null;
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
        throw new SystemException(ResponseCodeEnum.CONN_FTP_FAIL);
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
