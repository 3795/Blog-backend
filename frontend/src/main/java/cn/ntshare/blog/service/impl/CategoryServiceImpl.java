package cn.ntshare.blog.service.impl;

import cn.ntshare.blog.exception.SystemException;
import cn.ntshare.blog.dao.CategoryMapper;
import cn.ntshare.blog.dto.CategoryDTO;
import cn.ntshare.blog.dto.CategoryInfo;
import cn.ntshare.blog.dto.ChildrenCateDTO;
import cn.ntshare.blog.dto.ParentCateDTO;
import cn.ntshare.blog.enums.CommonStatusEnum;
import cn.ntshare.blog.enums.ResponseCodeEnum;
import cn.ntshare.blog.pojo.Category;
import cn.ntshare.blog.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
        return new PageInfo<>(categoryList);
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
        return new PageInfo<>(categoryList);
    }

    @Override
    public List<CategoryDTO> selectFirstLevel() {
        return categoryMapper.selectFirstLevel();
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

    @Override
    public int count() {
        return categoryMapper.count();
    }
}
