package cn.ntshare.blog.service.impl;

import cn.ntshare.blog.constant.SystemConstant;
import cn.ntshare.blog.dao.ArticleMapper;
import cn.ntshare.blog.dto.ArticleDTO;
import cn.ntshare.blog.dto.CategoryDTO;
import cn.ntshare.blog.dto.TagDTO;
import cn.ntshare.blog.enums.ResponseCodeEnum;
import cn.ntshare.blog.exception.SystemException;
import cn.ntshare.blog.service.ArticleService;
import cn.ntshare.blog.service.AsyncService;
import cn.ntshare.blog.service.CategoryService;
import cn.ntshare.blog.util.JsonUtil;
import cn.ntshare.blog.util.RedisUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 文章管理服务实现
 * Created At 2018/08/08
 */
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AsyncService asyncService;

    @Override
    public PageInfo selectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleDTO> articleDTOList = articleMapper.selectAll();
        return new PageInfo<>(articleDTOList);
    }

    @Override
    public ArticleDTO selectById(Integer id) {
        ArticleDTO articleDTO;
        // 优先读取缓存
        String articleStr = RedisUtil.get(SystemConstant.ARTICLE_CACHE_PREFIX + id.toString());
        if (articleStr == null) {
            articleDTO = articleMapper.selectById(id);
            if (articleDTO == null) {
                throw new SystemException(ResponseCodeEnum.PAGE_NOT_FOUND);
            }
            List<TagDTO> tags = articleMapper.queryTagsById(id);
            articleDTO.setTags(tags);

            // 将文章内容写入redis缓存
            articleStr = JsonUtil.obj2String(articleDTO);
            RedisUtil.setExpireTime(SystemConstant.ARTICLE_CACHE_PREFIX + id.toString(), articleStr, 180*SystemConstant.MINUTE);
        } else {
            articleDTO = JsonUtil.string2Obj(articleStr, ArticleDTO.class);
        }

        // 异步增加浏览量和访问量
        asyncService.increasePageViews(id);

        return articleDTO;
    }

    @Override
    public PageInfo selectPublished(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleDTO> articleDTOList = articleMapper.selectPublished();
        return new PageInfo<>(articleDTOList);
    }

    @Override
    public PageInfo search(String keywords, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleDTO> articleDTOList = articleMapper.search(keywords);
        return new PageInfo<>(articleDTOList);
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
        return new PageInfo<>(articleDTOList);
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

    @Override
    public void increasePageViews(Integer id) {
        articleMapper.increasePageViews(id);
    }
}
