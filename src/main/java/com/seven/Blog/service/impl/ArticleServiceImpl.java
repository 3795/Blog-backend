package com.seven.Blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seven.Blog.Exception.SystemException;
import com.seven.Blog.dao.ArticleMapper;
import com.seven.Blog.dto.ArticleDTO;
import com.seven.Blog.dto.CategoryDTO;
import com.seven.Blog.enums.ResponseCodeEnum;
import com.seven.Blog.pojo.Article;
import com.seven.Blog.service.ArticleService;
import com.seven.Blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 文章管理服务实现
 * Created At 2018/08/08
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CategoryService categoryService;

    @Override
    public PageInfo selectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleDTO> articleDTOList = articleMapper.selectAll();
        return new PageInfo<>(articleDTOList);
    }

    @Override
    public ArticleDTO selectById(Integer id) {
        ArticleDTO articleDTO = articleMapper.selectById(id);
        if (articleDTO == null) {
            throw new SystemException(ResponseCodeEnum.PAGE_NOT_FOUND);
        }
        return articleDTO;
    }

    @Override
    public PageInfo selectBriefInfoByTypeAndStatus(Integer type, Integer status, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleDTO> articleDTOList = articleMapper.selectBriefInfoByTypeAndStatus(type, status);
        return new PageInfo<>(articleDTOList);
    }

    @Override
    public boolean insert(Article article) {
        int result = articleMapper.insert(article);
        if (result != 1) {
            throw new SystemException(ResponseCodeEnum.INSERT_FAILED);
        }
        return true;
    }

    @Override
    public boolean update(Article article) {
        int result = articleMapper.update(article);
        if (result != 1) {
            throw new SystemException(ResponseCodeEnum.UPDATE_FAILED);
        }
        return true;
    }

    @Override
    public boolean updateStatus(Integer id, Integer status) {
        int result = articleMapper.updateStatus(id, status);
        if (result != 1) {
            throw new SystemException(ResponseCodeEnum.UPDATE_FAILED);
        }
        return true;
    }

    @Override
    public boolean updateType(Integer id, Integer type) {
        int result = articleMapper.updateType(id, type);
        if (result != 1) {
            throw new SystemException(ResponseCodeEnum.UPDATE_FAILED);
        }
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        int result = articleMapper.delete(id);
        if (result != 1) {
            throw new SystemException(ResponseCodeEnum.DELETE_FAILED);
        }
        return true;
    }

    @Override
    public PageInfo selectPublished(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleDTO> articleDTOList = articleMapper.selectPublished();
        return new PageInfo(articleDTOList);
    }

    @Override
    public PageInfo search(String keywords, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleDTO> articleDTOList = articleMapper.search(keywords);
        return new PageInfo(articleDTOList);
    }

    @Override
    public PageInfo selectPublishedByCate(Integer parentId, int pageNum, int pageSize) {
        CategoryDTO category = categoryService.selectById(parentId);
        List<ArticleDTO> articleDTOList;

        // 该分类为一级分类
        if (category.getParentId() == 0) {
            List<Integer> idList = categoryService.selectChildrenId(category.getId());
            PageHelper.startPage(pageNum, pageSize);
            articleDTOList = articleMapper.selectPublishedByCategories(idList);
        } else {
            PageHelper.startPage(pageNum, pageSize);
            articleDTOList = articleMapper.selectPublishedByCategoryId(category.getId());
        }
        return new PageInfo(articleDTOList);
    }

    @Override
    public int countAll() {
        return articleMapper.count();
    }

    @Override
    public int countByStatusAndType(Integer status, Integer type) {
        return articleMapper.countByStatus(status, type);
    }

    @Override
    public ArticleDTO queryBriefInfoById(Integer id) {
        ArticleDTO articleDTO = articleMapper.queryBriefInfoById(id);
        if (articleDTO == null) {
            throw new SystemException(ResponseCodeEnum.PAGE_NOT_FOUND);
        }
        return articleDTO;
    }

    @Override
    public String queryContentById(Integer id) {
        String content = articleMapper.queryContentById(id);
        if (content == null) {
            throw new SystemException(ResponseCodeEnum.PAGE_NOT_FOUND);
        }
        return content;
    }
}
