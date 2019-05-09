package cn.ntshare.blog.dto;

import lombok.Data;

/**
 * Created By Q.Hao
 * Description:
 * Created At 2019/5/7
 */
@Data
public class CategoryDTO {
    private Integer id;

    private String name;

    private Integer parentId;

    private String parentName;

    private Integer status;
}
