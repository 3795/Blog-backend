package cn.ntshare.blog.pojo;

import lombok.Data;

/**
 * Created By Q.Hao
 * Description: 标签Model
 * Created At 2019/4/25
 */
@Data
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
