package cn.ntshare.Blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 向前端返回分类相关的信息
 * Created At 2018/10/12
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryInfo {

    private String name;

    private List<CategoryDTO> categoryDTOList;

}
