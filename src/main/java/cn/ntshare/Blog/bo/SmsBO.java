package cn.ntshare.Blog.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class SmsBO implements Serializable {

    private static final long serialVersionUID = 1316661030112509281L;

    private Integer smsType;

    private String phone;

    private String content;
}
