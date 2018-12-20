package com.seven.Blog.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * Created By Seven.wk
 * Description: 子分类
 * Created At 2018/11/27
 */
@Getter
@Setter
@ToString
public class ChildrenCateDTO {

    private Integer value;

    private String label;

    private boolean isIsLeaf;

    public ChildrenCateDTO() {
        this.isIsLeaf = true;
    }
}
