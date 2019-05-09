package cn.ntshare.blog.dto;

import lombok.Data;

import java.util.List;

/**
 * Created By Q.Hao
 * Description: 父分类
 * Created At 2019/5/7
 */
@Data
public class ParentCateDTO {
    private Integer value;

    private String label;

    private List<ChildrenCateDTO> children;
}
