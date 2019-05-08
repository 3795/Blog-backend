package cn.ntshare.blog.pojo;

import lombok.Data;

@Data
public class Category {
    private Integer id;

    private String name;

    private Integer parentId;

    private Integer status;

    public Category(String name, Integer parentId, Integer status) {
        this.name = name;
        this.parentId = parentId;
        this.status = status;
    }
}
