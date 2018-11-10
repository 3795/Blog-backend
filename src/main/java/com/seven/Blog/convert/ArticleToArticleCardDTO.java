package com.seven.Blog.convert;

import com.seven.Blog.dto.ArticleCardDTO;
import com.seven.Blog.pojo.Article;
import com.seven.Blog.service.CategoryService;
import com.seven.Blog.util.ConstUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created By Seven.wk
 * Description: 将Article模型转换为ArticleCardDTO模型
 * Created At 2018/10/04
 */
@Component
public class ArticleToArticleCardDTO {

    @Autowired
    private CategoryService categoryService;

    public ArticleCardDTO convert(Article article) {
        ArticleCardDTO articleCardDTO = new ArticleCardDTO();
        BeanUtils.copyProperties(article, articleCardDTO);
        String categoryName = categoryService.getCategoryNameById(article.getCategoryId());
        if(categoryName == null)
            articleCardDTO.setCategoryName(ConstUtil.DEFAULT_CATEGORY_NAME);
        else
            articleCardDTO.setCategoryName(categoryName);
        return articleCardDTO;
    }

    public List<ArticleCardDTO> convert(List<Article> articleList) {
        return articleList.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
