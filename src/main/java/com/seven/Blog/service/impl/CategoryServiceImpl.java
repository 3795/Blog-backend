package com.seven.Blog.service.impl;

import com.seven.Blog.dao.CategoryMapper;
import com.seven.Blog.enums.ResponseCodeEnum;
import com.seven.Blog.pojo.Category;
import com.seven.Blog.response.ServerResponse;
import com.seven.Blog.service.CategoryService;
import com.seven.Blog.utils.Const;
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

    public List<Category> getAllCategory(int page, int size) {
        int offset = (page -1) * size;
        return categoryMapper.getAllCategory(offset, size);
    }

    @Override
    public List<Category> getAvailableCategory() {
        return categoryMapper.getCategoriesByStatus(Const.CategoryStatus.ABLE.getCode());
    }

    @Override
    public List<Category> getChildCategory(Integer parentId) {
        return categoryMapper.getChildCategory(parentId);
    }

    @Override
    public Category getCategoryById(Integer id) {
        return categoryMapper.selectedByPrimaryKey(id);
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
        int result = categoryMapper.updateCategory(category);
        if(result == 1)
            return ServerResponse.success("更新分类成功");
        return ServerResponse.error("更新分类失败");
    }

    @Override
    public ServerResponse deleteCategory(Integer id) {
        int result = categoryMapper.deleteCategoryByPrimaryKey(id);
        if(result == 1)
            return ServerResponse.success("删除分类成功");
        return ServerResponse.error("删除分类失败");
    }

    @Override
    public ServerResponse changeCategoryStatus(Integer id) {
        Category category = categoryMapper.selectedByPrimaryKey(id);
        if(category.getStatus() == Const.CategoryStatus.ABLE.getCode())
            category.setStatus(Const.CategoryStatus.DISABLE.getCode());
        else
            category.setStatus(Const.CategoryStatus.ABLE.getCode());
        int result = categoryMapper.updateCategory(category);
        if(result == 1)
            return ServerResponse.success("更改状态成功");
        return ServerResponse.error("更改状态失败");
    }

    @Override
    public String getCategoryNameById(Integer id) {
        return categoryMapper.selectedCategoryNameByPrimaryKey(id);
    }

    @Override
    public Integer getCategoryCount() {
        return categoryMapper.getCategoryCount();
    }
}
