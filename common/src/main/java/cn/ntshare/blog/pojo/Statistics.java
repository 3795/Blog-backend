package cn.ntshare.blog.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Created By Q.Hao
 * Description: 系统统计信息
 * Created At 2019/4/25
 */
@Data
public class Statistics {

    private Integer id;

    // 浏览量
    private Integer views;

    private Date day;

    private String month;

    private Integer year;

    public Statistics(Integer views) {
        this.views = views;
        this.day = new Date();
    }

    public Statistics(Integer views, Date day) {
        this.views = views;
        this.day = day;
    }

    public Statistics(Integer views, String month) {
        this.views = views;
        this.month = month;
    }

    public Statistics(Integer views, Integer year) {
        this.views = views;
        this.year = year;
    }
}
