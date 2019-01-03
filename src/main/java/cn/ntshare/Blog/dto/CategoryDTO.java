package cn.ntshare.Blog.dto;

import lombok.*;

/**
 * Created By Seven.wk
 * Description: 分类传输模型
 * Created At 2018/10/12
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryDTO {

    private Integer id;

    private String name;

    private Integer parentId;

    private String parentName;

    private Integer status;
}
