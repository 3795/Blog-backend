package cn.ntshare.blog.dto;

import lombok.Data;

/**
 * Created By Q.Hao
 * Description: 子分类信息
 * Created At 2019/5/7
 */
@Data
public class ChildrenCateDTO {
    private Integer value;

    private String label;

    private boolean isIsLeaf;

    public ChildrenCateDTO() {
        this.isIsLeaf = true;
    }
}
