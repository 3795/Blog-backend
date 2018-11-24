package com.seven.Blog.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * Created By Seven.wk
 * Description: 导航信息表单验证
 * Created At 2018/08/10
 */
@Getter
@Setter
@NoArgsConstructor
public class NavigationForm {

    @NotEmpty(message = "名称不能为空")
    private String name;

    @NotEmpty(message = "优先级不能为空")
    private String priority;

    @NotEmpty(message = "链接地址不能为空")
    private String link;

    @NotEmpty(message = "状态不能为空")
    private String status;

}
