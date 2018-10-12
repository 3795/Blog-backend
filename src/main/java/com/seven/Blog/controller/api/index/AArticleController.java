package com.seven.Blog.controller.api.index;

import com.seven.Blog.convert.ArticleToArticleCardDTO;
import com.seven.Blog.convert.ArticleToArticleDTO;
import com.seven.Blog.dto.ArticleCardDTO;
import com.seven.Blog.dto.ArticleCardsDTO;
import com.seven.Blog.dto.ArticleDTO;
import com.seven.Blog.enums.ResponseCodeEnum;
import com.seven.Blog.pojo.Article;
import com.seven.Blog.service.ArticleService;
import com.seven.Blog.utils.BasicUtil;
import com.seven.Blog.utils.ConstUtil;
import com.seven.Blog.vo.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 文章服务接口
 * Created At 2018/10/04
 */
@RestController
@RequestMapping("/api/index/article")
public class AArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleToArticleDTO articleToArticleDTO;

    @Autowired
    private ArticleToArticleCardDTO articleToArticleCardDTO;

    private final Integer size = 5;     //默认的文章显示页数

    @GetMapping
    public ServerResponse getArticles(@RequestParam(value = "page", defaultValue = "1") Integer page) {
        Integer maxPage = (int) Math.ceil((float)articleService.getArticleCountByStatus(ConstUtil.ArticleStatus.PUBLISHED.getCode()) / size);
        page = BasicUtil.getPage(page, maxPage);
        List<Article> articleList = articleService.getAllPublishedArticle(page, size);
        List<ArticleCardDTO> articleCardDTOList = articleToArticleCardDTO.convert(articleList);
        ArticleCardsDTO articleCardsDTO = new ArticleCardsDTO(articleCardDTOList, page, maxPage);
        return ServerResponse.success(articleCardsDTO);
    }

    @GetMapping("/{id}")
    public ServerResponse getArticleById(@PathVariable("id") Integer id) {
        Article article = articleService.getPublishedArticleByPrimaryKey(id);
        if(article == null)
            return ServerResponse.error(ResponseCodeEnum.PAGE_NOT_FOUND);

        ArticleDTO articleDTO = articleToArticleDTO.convert(article);
        return ServerResponse.success(articleDTO);
    }

    @GetMapping("/search")
    public ServerResponse search(@RequestParam("keywords") String keywords,
                                 @RequestParam(value = "page", defaultValue = "1") Integer page) {
        int maxPage = (int) Math.ceil((float)articleService.getArticleCountByKeywords(keywords) / size);
        page = BasicUtil.getPage(page, maxPage);
        List<Article> articleList = articleService.getPublishedArticleByKeywords(keywords, page, size);
        List<ArticleCardDTO> articleCardDTOS = articleToArticleCardDTO.convert(articleList);
        ArticleCardsDTO articleCardsDTO = new ArticleCardsDTO(articleCardDTOS, page, maxPage);
        return ServerResponse.success(articleCardsDTO);
    }
}
