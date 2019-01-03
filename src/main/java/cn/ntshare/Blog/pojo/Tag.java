package cn.ntshare.Blog.pojo;

import lombok.*;

/**
 * Created By Seven.wk
 * Description: 标签pojo类
 * Created At 2018/12/24
 */
@Data
@AllArgsConstructor
public class Tag {

    private Integer id;

    private String name;

    private String color;

    private Integer status = 1;

    public Tag(Integer id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public Tag(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Tag(String name, String color, Integer status) {
        this.name = name;
        this.color = color;
        this.status = status;
    }
}
