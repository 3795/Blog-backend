package com.seven.Blog.service;

import com.seven.Blog.pojo.Category;
import com.seven.Blog.response.ServerResponse;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 文章分类接口
 * Created At 2018/08/07
 */
public interface CategoryService {

    List<Category> getAllCategory();

    List<Category> getAvailableCategory();

    List<Category> getChildCategory(Integer parentId);

    Category getCategoryById(Integer id);

    ServerResponse addCategory(String name, Integer parentId, Integer status);

    ServerResponse updateCategory(Integer id, String name, Integer parentId, Integer status);

    ServerResponse deleteCategory(Integer id);

    ServerResponse changeCategoryStatus(Integer id);

    String getCategoryNameById(Integer id);
}
