package cn.ntshare.blog.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created By Seven.wk
 * Description: 新增tag的表单
 * Created At 2018/12/24
 */
@Data
public class TagForm {

    @NotEmpty(message = "名称不能为空")
    private String name;

    private String color;

}
