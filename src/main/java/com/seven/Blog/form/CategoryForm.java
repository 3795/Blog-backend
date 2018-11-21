package com.seven.Blog.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * Created By Seven.wk
 * Description: 文章分类信息表单
 * Created At 2018/08/07
 */
@Getter
@Setter
@NoArgsConstructor
public class CategoryForm {

    @NotEmpty(message = "分类名称不能为空")
    private String name;

    @NotEmpty(message = "父类ID不能为空")
    private String parentId;

    @NotEmpty(message = "分类状态不能为空")
    private String status;

}
