package com.seven.Blog.service.impl;

import com.seven.Blog.dao.CategoryMapper;
import com.seven.Blog.enums.ResponseCodeEnum;
import com.seven.Blog.pojo.Category;
import com.seven.Blog.response.ServerResponse;
import com.seven.Blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 文章分类接口实现
 * Created At 2018/08/07
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> getAllCategory() {
        return categoryMapper.getAllCategory();
    }

    @Override
    public ServerResponse addCategory(String name, Integer parentId, Integer status) {
        int result = categoryMapper.addCategory(name, parentId, status);
        if(result == 1)
            return ServerResponse.success("添加分类成功");
        return ServerResponse.error("添加分类失败");
    }

    @Override
    public ServerResponse updateCategory(Integer id, String name, Integer parentId, Integer status) {
        Category category = new Category(id, name, parentId, status);
        int result = categoryMapper.updateCategoryByPrimaryKey(category);
        if(result == 1)
            return ServerResponse.success("更新分类成功");
        return ServerResponse.error("更新分类失败");
    }
}
