package cn.ntshare.blog.dto;

import lombok.Data;

import java.util.List;

/**
 * Created By Q.Hao
 * Description: 向前端返回分类相关的信息
 * Created At 2019/5/7
 */
@Data
public class CategoryInfo {
    private String name;

    private List<CategoryDTO> categoryDTOList;
}
