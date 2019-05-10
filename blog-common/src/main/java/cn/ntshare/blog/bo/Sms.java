package cn.ntshare.blog.bo;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sms implements Serializable {

    private static final long serialVersionUID = 2168189751738034834L;

    String phone;

    String content;

}
