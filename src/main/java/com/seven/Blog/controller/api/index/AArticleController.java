package com.seven.Blog.controller.api.index;

import com.seven.Blog.convert.ArticleToArticleCardDTO;
import com.seven.Blog.dto.ArticleCardDTO;
import com.seven.Blog.dto.ArticleCardsDTO;
import com.seven.Blog.pojo.Article;
import com.seven.Blog.service.ArticleService;
import com.seven.Blog.utils.BasicUtil;
import com.seven.Blog.utils.ConstUtil;
import com.seven.Blog.vo.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    private ArticleToArticleCardDTO articleToArticleCardDTO;

    private final Integer size = 5;     //默认的文章显示页数

    @GetMapping
    public ServerResponse getArticles(@RequestParam(value = "page", defaultValue = "1") Integer page) {
        Integer maxPage = (int) Math.ceil((float)articleService.getArticleCountByStatus(ConstUtil.ArticleStatus.PUBLISHED.getCode()) / size);
        page = BasicUtil.getPage(page, maxPage);
        List<Article> articleList = articleService.getAllPublishedArticle(page, size);
        List<ArticleCardDTO> articleCardDTOList = articleToArticleCardDTO.convert(articleList);
        ArticleCardsDTO articleCardsDTO = new ArticleCardsDTO(articleCardDTOList, page, maxPage, "?page=");
        return ServerResponse.success(articleCardsDTO);
    }
}
