package com.seven.Blog.service;

import com.github.pagehelper.PageInfo;
import com.seven.Blog.bo.ParentCateBO;
import com.seven.Blog.dto.CategoryDTO;
import com.seven.Blog.dto.CategoryInfo;
import com.seven.Blog.pojo.Category;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 文章分类接口
 * Created At 2018/08/07
 */
public interface CategoryService {

    PageInfo selectAll(int pageNum, int pageSize);

    CategoryDTO selectById(Integer id);

    PageInfo selectByStatus(int status, int pageNum, int pageSize);

    List<CategoryDTO> selectFirstLevel();

    Boolean insert(Category category);

    Boolean update(Category category);

    Boolean changeStatus(Integer id);

    Boolean delete(Integer id);

    List<ParentCateBO> selectCascadeCate();

    List<Integer> selectChildrenId(Integer id);

    CategoryInfo selectParentAndChildren(Integer id);

    int count();
}
