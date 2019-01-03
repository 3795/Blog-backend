package cn.ntshare.Blog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created By Seven.wk
 * Description: 导航信息传输模型
 * Created At 2018/10/04
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class NavigationDTO {

    private Integer id;

    private String name;

    private Integer priority;

    private String link;

    private Integer status;

}
