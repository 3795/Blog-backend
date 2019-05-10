package cn.ntshare.blog.bo;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Email implements Serializable {

    private static final long serialVersionUID = -1861767976304443172L;

    private String to;

    private String subject;

    private String content;
}
