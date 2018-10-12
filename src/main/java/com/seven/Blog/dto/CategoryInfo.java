package com.seven.Blog.dto;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 向前端返回分类相关的信息
 * Created At 2018/10/12
 */
public class CategoryInfo {
    private String name;
    private List<CategoryDTO> categoryDtos;

    public CategoryInfo(String name) {
        this.name = name;
    }

    public CategoryInfo(String name, List<CategoryDTO> categoryDtos) {
        this.name = name;
        this.categoryDtos = categoryDtos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CategoryDTO> getCategoryDtos() {
        return categoryDtos;
    }

    public void setCategoryDtos(List<CategoryDTO> categoryDtos) {
        this.categoryDtos = categoryDtos;
    }
}
