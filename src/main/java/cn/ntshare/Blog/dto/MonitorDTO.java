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

    private Integer articleCount;

    private Integer categoryCount;

    private Integer tagCount;

    public MonitorDTO(Integer articleCount, Integer categoryCount, Integer tagCount) {
        this.articleCount = articleCount;
        this.categoryCount = categoryCount;
        this.tagCount = tagCount;
    }
}
