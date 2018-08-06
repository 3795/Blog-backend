package com.seven.Blog.utils;

import org.apache.logging.log4j.util.PropertiesUtil;

import java.security.MessageDigest;

/**
 * Created By Seven.wk
 * Description: MD5加密工具
 * Created At 2018/08/06
 */
public class MD5Util {
    //16进制对应的符号
    private static final String hexDigits[] = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"
    };

    /**
     * 将单个字节数据转化为16进制数据
     * @param b     字节
     * @return      转化后的16进制数据
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if(n < 0)
            n += 256;
        int d1 = n /16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * 将字节数组转化为对应的十六进制数据
     * @param b     字节数组
     * @return      十六进制数据
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte aB : b) {
            resultSb.append(byteToHexString(aB));
        }
        return resultSb.toString();
    }

    /**
     * 进行MD5加密
     * @param origin        原始数据
     * @param charsetName   编码方式
     * @return      大写的MD5加密值
     */
    private static String MD5Encode(String origin, String charsetName) {
        String resultString = null;
        try{
            resultString = origin;
            MessageDigest md = MessageDigest.getInstance("MD5");
            if(charsetName == null || charsetName.equals(""))
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetName)));
        }catch(Exception e) {
            e.printStackTrace();
        }
        return resultString.toUpperCase();
    }

    /**
     * 使用utf-8的方式对数据进行MD5加密
     * @param origin        原始数据
     * @return      加密后的数据
     */
    public static String MD5EncodeUtf8(String origin) {
        return MD5Encode(origin, "utf-8");
    }


}
