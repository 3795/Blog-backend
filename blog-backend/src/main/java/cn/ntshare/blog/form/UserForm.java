package cn.ntshare.blog.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * Created By Seven.wk
 * Description: 用户信息表单
 * Created At 2018/12/27
 */
@Getter
@Setter
@NoArgsConstructor
public class UserForm {

    @NotEmpty(message = "用户名不能为空")
    private String username;

    @NotEmpty(message = "邮箱不能为空")
    private String email;

    @NotEmpty(message = "手机号不能为空")
    private String phone;

    private String signature;
}
