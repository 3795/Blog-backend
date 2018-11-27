package com.seven.Blog.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class ChildrenCateBO {

    private Integer value;

    private String label;

    @JsonProperty(value = "isLeaf")
    private boolean isLeaf;

    public ChildrenCateBO() {
        this.isLeaf = true;
    }
}
