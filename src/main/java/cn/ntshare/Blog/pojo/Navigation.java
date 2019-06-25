package cn.ntshare.Blog.pojo;

import lombok.*;

import java.io.Serializable;

/**
 * Created By Seven.wk
 * Description: 前端导航类实体
 * Created At 2018/08/10
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Navigation implements Serializable {

    private static final long serialVersionUID = 4696820801835961503L;

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
