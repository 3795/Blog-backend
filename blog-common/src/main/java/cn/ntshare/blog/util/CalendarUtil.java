package cn.ntshare.blog.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created By Seven.wk
 * Description: 日期工具
 * Created At 2019/01/16
 */
public class CalendarUtil {

    /**
     * 获取今天的日期，格式为年-月-日
     * @return
     */
    public static String getToday() {
        Calendar calendar = Calendar.getInstance();
        return new SimpleDateFormat( "yyyy-MM-dd").format(calendar.getTime());
    }

    /**
     * 获取昨天的日期，格式为年-月-日
     * @return
     */
    public static String getYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return new SimpleDateFormat( "yyyy-MM-dd").format(calendar.getTime());
    }

    /**
     * 获取明天的日期，格式为年-月-日
     * @return
     */
    public static String getTomorrow() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, +1);
        return new SimpleDateFormat( "yyyy-MM-dd").format(calendar.getTime());
    }

    /**
     * 获取明天的日期，格式为年-月-日
     * @return
     */
    public static Date getTomorrowDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, +1);
        return calendar.getTime();
    }

    /**
     * 获取当前月份的日期，格式为年-月
     * @return
     */
    public static String getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return new SimpleDateFormat( "yyyy-MM").format(calendar.getTime());
    }

    /**
     * 获取下一个月份的日期，格式为年-月
     * @return
     */
    public static String getNextMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, +1);
        return new SimpleDateFormat( "yyyy-MM").format(calendar.getTime());
    }

}
