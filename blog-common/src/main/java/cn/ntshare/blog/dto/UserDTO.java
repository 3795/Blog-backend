package cn.ntshare.blog.dto;

import lombok.Data;

/**
 * Created By Q.Hao
 * Description: 用户信息传输模型
 * Created At 2019/5/7
 */
@Data
public class UserDTO {
    private Integer id;

    private String account;

    private String username;

    private String avatar;

    private String email;

    private String phone;

    private String signature;

    public UserDTO(String username, String avatar) {
        this.username = username;
        this.avatar = avatar;
    }
}
