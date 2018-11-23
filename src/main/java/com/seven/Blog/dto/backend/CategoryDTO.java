package com.seven.Blog.dto.backend;

import lombok.*;

/**
 * Created By Seven.wk
 * Description: 分类管理传输模型
 * Created At 2018/11/23
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryDTO{

    private Integer id;

    private String name;

    private Integer parentId;

    private String parentName;

    private Integer status;

}
