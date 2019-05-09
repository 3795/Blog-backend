package cn.ntshare.blog.service.impl;

import cn.ntshare.blog.constant.SystemConstant;
import cn.ntshare.blog.dao.ArticleMapper;
import cn.ntshare.blog.dto.ArticleDTO;
import cn.ntshare.blog.dto.TagDTO;
import cn.ntshare.blog.enums.ResponseCodeEnum;
import cn.ntshare.blog.exception.SystemException;
import cn.ntshare.blog.pojo.Article;
import cn.ntshare.blog.service.ArticleService;
import cn.ntshare.blog.service.AsyncService;
import cn.ntshare.blog.service.ImgRecordService;
import cn.ntshare.blog.util.JsonUtil;
import cn.ntshare.blog.util.RedisUtil;
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
    public PageInfo selectBriefInfoByTypeAndStatus(Integer type, Integer status, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleDTO> articleDTOList = articleMapper.selectBriefInfoByTypeAndStatus(type, status);
        return new PageInfo<>(articleDTOList);
    }

    @Override
    @Transactional
    public void insert(Article article, List<Integer> tags) {
        // 新增文章数据
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

        // 将文章url写入redis
        String url = SystemConstant.WEB_URL + "/article/" + article.getId();
        RedisUtil.setList(SystemConstant.INDEX_LINKS, url);


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

        // 更新redis记录
        Integer articleId = article.getId();
        String articleStr = RedisUtil.get(SystemConstant.ARTICLE_CACHE_PREFIX + articleId.toString());
        if (articleStr != null) {
            ArticleDTO articleDTO = JsonUtil.string2Obj(articleStr, ArticleDTO.class);
            if (articleDTO != null) {
                articleDTO.setTitle(article.getTitle());
                articleDTO.setSummary(article.getSummary());
                articleDTO.setContent(article.getContent());
                articleStr = JsonUtil.obj2String(articleDTO);
                RedisUtil.setExpireTime(SystemConstant.ARTICLE_CACHE_PREFIX + articleId.toString(), articleStr, 180*SystemConstant.MINUTE);
                log.info("文章缓存更新成功, ID = " + articleId);
            } else {
                log.error("文章反序列失败，ID = " + articleId);
            }
        }
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
    public int countAll() {
        return articleMapper.count();
    }

    @Override
    public int countByStatusAndType(Integer status, Integer type) {
        return articleMapper.countByStatus(status, type);
    }

    @Override
    public void increasePageViews(Integer id) {
        articleMapper.increasePageViews(id);
    }
}
