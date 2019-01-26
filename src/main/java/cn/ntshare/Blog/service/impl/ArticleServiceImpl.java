package cn.ntshare.Blog.service.impl;

import cn.ntshare.Blog.constant.SystemConstant;
import cn.ntshare.Blog.dao.ArticleMapper;
import cn.ntshare.Blog.dto.ArticleDTO;
import cn.ntshare.Blog.dto.CategoryDTO;
import cn.ntshare.Blog.dto.TagDTO;
import cn.ntshare.Blog.enums.ResponseCodeEnum;
import cn.ntshare.Blog.exception.SystemException;
import cn.ntshare.Blog.pojo.Article;
import cn.ntshare.Blog.service.ArticleService;
import cn.ntshare.Blog.service.AsyncService;
import cn.ntshare.Blog.service.CategoryService;
import cn.ntshare.Blog.service.ImgRecordService;
import cn.ntshare.Blog.util.JsonUtil;
import cn.ntshare.Blog.util.RedisPoolUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

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
    private ImgRecordService imgRecordService;

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
        String articleStr = RedisPoolUtil.get(SystemConstant.ARTICLE_CACHE_PREFIX + id.toString());
        if (articleStr == null) {
            articleDTO = articleMapper.selectById(id);
            if (articleDTO == null) {
                throw new SystemException(ResponseCodeEnum.PAGE_NOT_FOUND);
            }
            List<TagDTO> tags = articleMapper.queryTagsById(id);
            articleDTO.setTags(tags);

            // 将文章内容写入redis缓存
            articleStr = JsonUtil.obj2String(articleDTO);
            RedisPoolUtil.setExpireTime(SystemConstant.ARTICLE_CACHE_PREFIX + id.toString(), articleStr, 180*SystemConstant.MINUTE);
        } else {
            articleDTO = JsonUtil.string2Obj(articleStr, ArticleDTO.class);
        }

        // 异步增加浏览量和访问量
        asyncService.increasePageViews(id);

        return articleDTO;
    }

    @Override
    public PageInfo selectBriefInfoByTypeAndStatus(Integer type, Integer status, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleDTO> articleDTOList = articleMapper.selectBriefInfoByTypeAndStatus(type, status);
        return new PageInfo<>(articleDTOList);
    }

    @Override
    @Transactional
    public void insert(Article article, List<Integer> tags) {
        int result = articleMapper.insert(article);
        if (result != 1) {
            log.warn("insert article error!");
            throw new SystemException(ResponseCodeEnum.INSERT_FAILED);
        }

        // 添加标签关联
        result = articleMapper.insertArticleTag(article.getId(), tags);
        if (result < 1) {
            log.warn("insert article_tag error!");
            throw new SystemException(ResponseCodeEnum.INSERT_FAILED);
        }

        // 添加文章浏览量
        Random random = new Random();
        int pageViews = random.nextInt(25) + 5;
        result = articleMapper.insertArticlePageViews(article.getId(), pageViews);
        if (result != 1) {
            log.warn("insert article_pageviews error!");
            throw new SystemException(ResponseCodeEnum.INSERT_FAILED);
        }

        // 添加图片记录
        imgRecordService.updateArticleIdByImg(article.getId(), article.getImg());

    }

    @Override
    @Transactional
    public void update(Article article, List<Integer> tags) {
        int result = articleMapper.update(article);
        if (result != 1) {
            log.warn("update article error!");
            throw new SystemException(ResponseCodeEnum.UPDATE_FAILED);
        }

        // 更新与标签之间的关联
        articleMapper.deleteArticleTagByArticleId(article.getId());
        result = articleMapper.insertArticleTag(article.getId(), tags);
        if (result < 1) {
            log.warn("update article_tag error!");
            throw new SystemException(ResponseCodeEnum.UPDATE_FAILED);
        }

        // 更新图片记录
        imgRecordService.updateImgByArticleId(article.getId(), article.getImg());

    }

    @Override
    public void updateStatus(Integer id, Integer status) {
        int result = articleMapper.updateStatus(id, status);
        if (result != 1) {
            throw new SystemException(ResponseCodeEnum.UPDATE_FAILED);
        }
    }

    @Override
    public void updateType(Integer id, Integer type) {
        int result = articleMapper.updateType(id, type);
        if (result != 1) {
            throw new SystemException(ResponseCodeEnum.UPDATE_FAILED);
        }
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        int result = articleMapper.delete(id);
        if (result != 1) {
            log.error("delete article error!");
            throw new SystemException(ResponseCodeEnum.DELETE_FAILED);
        }

        // 删除文章与标签之间的关联
        articleMapper.deleteArticleTagByArticleId(id);

        // 删除浏览量记录
        result = articleMapper.deleteArticlePageViews(id);
        if (result != 1) {
            log.error("delete article_pageviews error!");
            throw new SystemException(ResponseCodeEnum.DELETE_FAILED);
        }

        // 删除图片记录
        imgRecordService.deleteArticleId(id);

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

    @Override
    public void increasePageViews(Integer id) {
        articleMapper.increasePageViews(id);
    }
}
