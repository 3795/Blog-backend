package cn.ntshare.Blog.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Created By Seven.wk
 * Description: 系统统计数据实体
 * Created At 2019/01/15
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Statistics implements Serializable {

    private static final long serialVersionUID = -7017770222084768042L;

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
