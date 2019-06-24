package cn.ntshare.Blog.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailBO implements Serializable {
    private static final long serialVersionUID = -6624099777805107523L;

    private String to;

    private String subject;

    private String content;
}
