package cn.ntshare.Blog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created By Seven.wk
 * Description: 标签DTO
 * Created At 2018/12/29
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
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
