package cn.ntshare.blog.dto;

import lombok.Data;

/**
 * Created By Q.Hao
 * Description:
 * Created At 2019/5/7
 */
@Data
public class NavigationDTO {
    private Integer id;

    private String name;

    private Integer priority;

    private String link;

    private Integer status;
}
