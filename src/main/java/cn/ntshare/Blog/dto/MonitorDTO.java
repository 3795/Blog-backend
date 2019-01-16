package cn.ntshare.Blog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created By Seven.wk
 * Description: 系统检测数据传输模型
 * Created At 2019/01/06
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
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

    public MonitorDTO(Integer articleCount, Integer categoryCount, Integer tagCount, Integer todayViews, Integer yesterdayViews) {
        this.articleCount = articleCount;
        this.categoryCount = categoryCount;
        this.tagCount = tagCount;
        this.todayViews = todayViews;
        this.yesterdayViews = yesterdayViews;
    }
}
