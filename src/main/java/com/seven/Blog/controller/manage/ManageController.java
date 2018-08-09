package com.seven.Blog.controller.manage;

import com.seven.Blog.dto.ArticleDTO;
import com.seven.Blog.pojo.Article;
import com.seven.Blog.pojo.Category;
import com.seven.Blog.service.ArticleService;
import com.seven.Blog.service.CategoryService;
import com.seven.Blog.utils.ArticleToArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created By Seven.wk
 * Description: 管理中心控制器
 * Created At 2018/08/07
 */
@Controller
@RequestMapping("/manage")
public class ManageController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleToArticleDTO converter;

    @GetMapping("/index")
    public ModelAndView index(Map<String, String> map) {
        map.put("title", "信息管理");
        return new ModelAndView("manage/manage/index", map);
    }

    @GetMapping("/category")
    public ModelAndView category(Map<String, Object> map) {
        List<Category> categoryList = categoryService.getAllCategory();
        map.put("title", "分类管理");
        map.put("categoryList", categoryList);
        return new ModelAndView("manage/manage/category", map);
    }

    @GetMapping("/article")
    public ModelAndView article(Map<String, Object> map) {
        List<Article> articleList = articleService.getAllArticles();
        List<ArticleDTO> articleDTOList = converter.convert(articleList);
        map.put("title", "管理文章");
        map.put("articleList", articleDTOList);
        return new ModelAndView("manage/manage/article", map);
    }

    @GetMapping("/article/add")
    public ModelAndView addArticle(Map<String, Object> map) {
        List<Category> categoryList = categoryService.getAvailableCategory();
        map.put("title", "新增文章");
        map.put("categoryList", categoryList);
        return new ModelAndView("manage/manage/addArticle", map);
    }

}
