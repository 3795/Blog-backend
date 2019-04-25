package cn.ntshare.blog.pojo;

import lombok.Data;

/**
 * Created By Q.Hao
 * Description:
 * Created At 2019/4/25
 */
@Data
public class Navigation {

    private Integer id;

    private String name;

    private Integer priority;

    private String link;

    private Integer status;

    public Navigation(String name, Integer priority, String link, Integer status) {
        this.name = name;
        this.priority = priority;
        this.link = link;
        this.status = status;
    }

}
