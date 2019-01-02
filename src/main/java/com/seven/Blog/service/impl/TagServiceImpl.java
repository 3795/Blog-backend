package com.seven.Blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seven.Blog.Exception.SystemException;
import com.seven.Blog.dao.TagMapper;
import com.seven.Blog.dto.ArticleDTO;
import com.seven.Blog.dto.TagDTO;
import com.seven.Blog.enums.CommonStatusEnum;
import com.seven.Blog.enums.ResponseCodeEnum;
import com.seven.Blog.pojo.Tag;
import com.seven.Blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 实现
 * Created At 2018/12/24
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public PageInfo queryTags(Integer status, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TagDTO> tagList = tagMapper.queryTags(status);
        return new PageInfo<>(tagList);
    }

    @Override
    public List<TagDTO> queryTags() {
        return tagMapper.queryEnableTags();
    }

    @Override
    public List<TagDTO> queryTagOptions() {
        return tagMapper.queryTagOptions();
    }

    @Override
    public TagDTO queryTagById(Integer id) {
        TagDTO tag = tagMapper.queryTagById(id);
        if (tag == null) {
            throw new SystemException(ResponseCodeEnum.PAGE_NOT_FOUND);
        }
        return tag;
    }

    @Override
    public boolean insertTag(Tag tag) {
        int result = tagMapper.insertTag(tag);
        if (result != 1) {
            throw new SystemException(ResponseCodeEnum.INSERT_FAILED);
        }
        return true;
    }

    @Override
    public boolean updateTag(Tag tag) {
        int result = tagMapper.updateTag(tag);
        if (result != 1) {
            throw new SystemException(ResponseCodeEnum.UPDATE_FAILED);
        }
        return true;
    }

    @Override
    public boolean updateStatus(Integer id) {
        int result = tagMapper.updateStatus(id);
        if (result != 1) {
            throw new SystemException(ResponseCodeEnum.UPDATE_FAILED);
        }
        return true;
    }

    @Override
    public boolean deleteTag(Integer id) {
        int result = tagMapper.deleteTag(id);
        if (result != 1) {
            throw new SystemException(ResponseCodeEnum.DELETE_FAILED);
        }
        return true;
    }

    @Override
    public PageInfo queryArticlesById(Integer id, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleDTO> list = tagMapper.queryArticlesById(id);
        return new PageInfo<>(list);
    }
}
