package com.seven.Blog.service;

import com.seven.Blog.pojo.Category;
import com.seven.Blog.vo.ServerResponse;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 文章分类接口
 * Created At 2018/08/07
 */
public interface CategoryService {

    List<Category> getAllCategory(int page, int size);

    List<Category> getAvailableCategory();

    List<Category> getChildCategory(Integer parentId);

    Category getCategoryById(Integer id);

    ServerResponse addCategory(String name, Integer parentId, Integer status);

    ServerResponse updateCategory(Integer id, String name, Integer parentId, Integer status);

    ServerResponse deleteCategory(Integer id);

    ServerResponse changeCategoryStatus(Integer id);

    String getCategoryNameById(Integer id);

    /**
     * 获取所有分类的数量
     * @return
     */
    Integer getCategoryCount();
}
