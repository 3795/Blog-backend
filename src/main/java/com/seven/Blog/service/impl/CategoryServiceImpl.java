package com.seven.Blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seven.Blog.Exception.SystemException;
import com.seven.Blog.dao.CategoryMapper;
import com.seven.Blog.dto.CategoryDTO;
import com.seven.Blog.enums.CommonStatusEnum;
import com.seven.Blog.enums.ResponseCodeEnum;
import com.seven.Blog.pojo.Category;
import com.seven.Blog.service.CategoryService;
import com.seven.Blog.util.ConstUtil;
import com.seven.Blog.vo.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 文章分类接口实现
 * Created At 2018/08/07
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> getAllCategory(int page, int size) {
        int offset = (page -1) * size;
        return categoryMapper.getAllCategory(offset, size);
    }

    @Override
    public List<Category> getAvailableCategory() {
        return categoryMapper.getCategoriesByStatus(ConstUtil.CategoryStatus.ABLE.getCode());
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
        if(category.getStatus() == ConstUtil.CategoryStatus.ABLE.getCode())
            category.setStatus(ConstUtil.CategoryStatus.DISABLE.getCode());
        else
            category.setStatus(ConstUtil.CategoryStatus.ABLE.getCode());
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

    /*---------------------------二期新增---------------------------------------*/

    @Override
    public PageInfo selectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CategoryDTO> categoryList = categoryMapper.selectAll();
        return new PageInfo(categoryList);
    }

    @Override
    public CategoryDTO selectById(Integer id) {
        CategoryDTO category = categoryMapper.selectById(id);
        if (category == null) {
            throw new SystemException(ResponseCodeEnum.PAGE_NOT_FOUND);
        }
        return category;
    }

    @Override
    public PageInfo selectByStatus(int status, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CategoryDTO> categoryList = categoryMapper.selectByStatus(status);
        return new PageInfo(categoryList);
    }

    @Override
    public List<CategoryDTO> selectFirstLevel() {
        return categoryMapper.selectFirstLevel();
    }

    @Override
    public Boolean insert(Category category) {
        int result = categoryMapper.insert(category);
        if (result != 1) {
            throw new SystemException(ResponseCodeEnum.INSERT_FAILED);
        }
        return true;
    }

    @Override
    public Boolean update(Category category) {
        int result = categoryMapper.update(category);
        if (result != 1) {
            log.warn("分类ID：{}", category.getId());
            throw new SystemException(ResponseCodeEnum.UPDATE_FAILED);
        }
        return true;
    }

    @Override
    public Boolean changeStatus(Integer id) {
        Integer status = categoryMapper.selectStatusById(id);
        if (status == null) {
            throw new SystemException(ResponseCodeEnum.PAGE_NOT_FOUND);
        }
        if ((int) status == CommonStatusEnum.OFF.getCode()) {
            status = CommonStatusEnum.ON.getCode();
        } else {
            status = CommonStatusEnum.OFF.getCode();
        }

        int result = categoryMapper.updateStatus(id, status);
        if (result != 1) {
            log.warn("分类ID：{}", id);
            throw new SystemException(ResponseCodeEnum.UPDATE_FAILED);
        }
        return true;
    }

    @Override
    public Boolean delete(Integer id) {
        int result = categoryMapper.delete(id);
        if (result != 1) {
            log.warn("分类ID：{}", id);
            throw new SystemException(ResponseCodeEnum.DELETE_FAILED);
        }
        return true;
    }
}
