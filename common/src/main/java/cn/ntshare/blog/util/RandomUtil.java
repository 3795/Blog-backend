package cn.ntshare.blog.util;

import java.util.Random;

/**
 * Created By Seven.wk
 * Description: 随机数工具
 * Created At 2019/01/09
 */
public class RandomUtil {

    /**
     * 得到随机数
     * @param num       随机数的位数
     * @return
     */
    public static String getRandomNumber(int num) {
        StringBuilder result = new StringBuilder();
        Random random = new Random(System.currentTimeMillis());
        for (int i=0; i<num; i++) {
            result.append(random.nextInt(10));
        }
        return result.toString();
    }

    /**
     * 得到全局唯一key值
     * @return
     */
    public static String getUniqueKey() {
        return String.valueOf(System.currentTimeMillis()) + getRandomNumber(6);
    }

}
