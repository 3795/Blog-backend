package cn.ntshare.blog.dto;

import lombok.Data;

/**
 * Created By Q.Hao
 * Description:
 * Created At 2019/5/7
 */
@Data
public class TagDTO {
    private Integer id;

    private String name;

    private String color;

    private Integer status;

    // 前端接口中的label属性
    private String label;

    // 前端接口中的value属性
    private Integer value;
}
