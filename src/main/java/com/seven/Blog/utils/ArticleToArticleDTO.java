package com.seven.Blog.utils;

import com.seven.Blog.dto.ArticleDTO;
import com.seven.Blog.pojo.Article;
import com.seven.Blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By Seven.wk
 * Description: 将Article模型转换为ArticleDTO模型
 * Created At 2018/08/08
 */
@Component
public class ArticleToArticleDTO {

    @Autowired
    private CategoryService categoryService;

    /**
     * 将单个Article模型转换为ArticleDTO模型
     * @param article
     * @return
     */
    public ArticleDTO convert(Article article) {
        ArticleDTO articleDTO = new ArticleDTO(article.getId(), article.getTitle(),
                article.getImg(), article.getSummary(), article.getContent(),
                article.getCreateTime(), article.getUpdateTime());
        String categoryName = categoryService.getCategoryNameById(article.getCategoryId());
        if(categoryName == null)
            articleDTO.setCategoryName(Const.DEFAULT_CATEGORY_NAME);
        else
            articleDTO.setCategoryName(categoryName);
        if(article.getStatus() == Const.ArticleStatus.UNPUBLISHED.getCode())
            articleDTO.setStatusMsg(Const.ArticleStatus.UNPUBLISHED.getDesc());
        else
            articleDTO.setStatusMsg(Const.ArticleStatus.PUBLISHED.getDesc());
        return articleDTO;
    }

    /**
     * 将Article列表转换为ArticleDTO列表
     * @param articleList
     * @return
     */
    public List<ArticleDTO> convert(List<Article> articleList) {
        List<ArticleDTO> articleDTOList = new ArrayList<>();
        for(Article article : articleList) {
            articleDTOList.add(convert(article));
        }
        return articleDTOList;
    }
}
