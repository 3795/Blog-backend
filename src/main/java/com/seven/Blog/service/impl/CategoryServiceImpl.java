package com.seven.Blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seven.Blog.Exception.SystemException;
import com.seven.Blog.bo.ChildrenCateBO;
import com.seven.Blog.bo.ParentCateBO;
import com.seven.Blog.dao.CategoryMapper;
import com.seven.Blog.dto.CategoryDTO;
import com.seven.Blog.dto.CategoryInfo;
import com.seven.Blog.enums.CommonStatusEnum;
import com.seven.Blog.enums.ResponseCodeEnum;
import com.seven.Blog.pojo.Category;
import com.seven.Blog.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<ParentCateBO> selectCascadeCate() {
        List<ParentCateBO> parentCateBOList = categoryMapper.selectParent();

        if (parentCateBOList != null) {
            for (ParentCateBO p : parentCateBOList) {
                List<ChildrenCateBO> childrenCateBOList = categoryMapper.selectChildren(p.getValue());
                p.setChildren(childrenCateBOList);
            }
        }

        return parentCateBOList;
    }

    @Override
    public List<Integer> selectChildrenId(Integer id) {
        return categoryMapper.selectChildrenId(id);
    }

    @Override
    public CategoryInfo selectParentAndChildren(Integer id) {
        CategoryDTO parentCate = this.selectById(id);
        List<CategoryDTO> childrenList = new ArrayList<>();
        if (parentCate.getParentId() == 0) {
            childrenList = categoryMapper.selectChildrenInfo(parentCate.getId());
        }
        return new CategoryInfo(parentCate.getName(), childrenList);
    }
}
