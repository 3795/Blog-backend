package cn.ntshare.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created By Q.Hao
 * Description: 监控数据传输模型
 * Created At 2019/5/7
 */
@Data
@AllArgsConstructor
public class MonitorDTO {
    // 文章总数
    private Integer articleCount;

    // 分类总数
    private Integer categoryCount;

    // 标签总数
    private Integer tagCount;

    // 今日访问量
    private Integer todayViews;

    // 昨日访问量
    private Integer yesterdayViews;

    // 本月访问量
    private Integer monthViews;
}
