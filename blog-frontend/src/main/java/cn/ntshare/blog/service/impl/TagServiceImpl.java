package cn.ntshare.blog.service.impl;

import cn.ntshare.blog.dao.TagMapper;
import cn.ntshare.blog.dto.ArticleDTO;
import cn.ntshare.blog.dto.TagDTO;
import cn.ntshare.blog.enums.ResponseCodeEnum;
import cn.ntshare.blog.exception.SystemException;
import cn.ntshare.blog.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public List<TagDTO> queryTags() {
        return tagMapper.queryEnableTags();
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
    public PageInfo queryArticlesById(Integer id, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleDTO> list = tagMapper.queryArticlesById(id);
        return new PageInfo<>(list);
    }

    @Override
    public int count() {
        return tagMapper.count();
    }
}
