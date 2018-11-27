package com.seven.Blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seven.Blog.Exception.SystemException;
import com.seven.Blog.dao.ArticleMapper;
import com.seven.Blog.dto.ArticleDTO;
import com.seven.Blog.enums.ResponseCodeEnum;
import com.seven.Blog.pojo.Article;
import com.seven.Blog.vo.ServerResponse;
import com.seven.Blog.service.ArticleService;
import com.seven.Blog.util.ConstUtil;
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

    @Override
    public PageInfo selectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleDTO> articleDTOList = articleMapper.selectAll();
        return new PageInfo(articleDTOList);
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
    public PageInfo selectBriefInfoByStatus(Integer status, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleDTO> articleDTOList = articleMapper.selectBriefInfoByStatus(status);
        return new PageInfo(articleDTOList);
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
}
