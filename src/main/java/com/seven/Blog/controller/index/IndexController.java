package com.seven.Blog.controller.index;

import com.seven.Blog.dto.ArticleDTO;
import com.seven.Blog.pojo.Article;
import com.seven.Blog.service.ArticleService;
import com.seven.Blog.utils.ArticleToArticleDTO;
import com.seven.Blog.utils.BasicUtil;
import com.seven.Blog.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created By Seven.wk
 * Description: 前台页面展示控制器
 * Created At 2018/08/11
 */
@Controller
public class IndexController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleToArticleDTO articleToArticleDTO;

    private Integer size = 5;       //设置每页默认显示4条文章

    /**
     * 首页展示内容
     * @param map
     * @param page
     * @return
     */
    @GetMapping("")
    public ModelAndView index(Map<String, Object> map,
                              @RequestParam(value = "page", defaultValue = "1") Integer page) {
        Integer maxPage = articleService.getArticleCount(Const.ArticleStatus.PUBLISHED.getCode());
        page = BasicUtil.getPage(page, maxPage);
        List<Article> articleList = articleService.getAllPublishedArticle(page, size);
        List<ArticleDTO> articleDTOList = articleToArticleDTO.convert(articleList);
        map.put("title", "NTShare");
        map.put("articleList", articleDTOList);
        return new ModelAndView("/index/index/templateA", map);
    }

    /**
     * 展示某一篇文章
     * @param map
     * @param id
     * @return
     */
    @GetMapping("/article/{id}")
    public ModelAndView article(Map<String, Object> map,
                                @PathVariable("id") Integer id) {
        Article article = articleService.getArticleByPrimaryKey(id);
        if(article == null)
            //todo 跳转到404
            return null;
        ArticleDTO articleDTO = articleToArticleDTO.convert(article);
        map.put("title", articleDTO.getTitle());
        map.put("article", articleDTO);
        return new ModelAndView("/index/index/article", map);
    }
}
