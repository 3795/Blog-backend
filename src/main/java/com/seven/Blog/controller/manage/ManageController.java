package com.seven.Blog.controller.manage;

import com.seven.Blog.dto.ArticleDTO;
import com.seven.Blog.pojo.Article;
import com.seven.Blog.pojo.Category;
import com.seven.Blog.pojo.Navigation;
import com.seven.Blog.pojo.User;
import com.seven.Blog.service.ArticleService;
import com.seven.Blog.service.CategoryService;
import com.seven.Blog.service.NavigationService;
import com.seven.Blog.service.UserService;
import com.seven.Blog.utils.ArticleToArticleDTO;
import com.seven.Blog.utils.BasicUtil;
import com.seven.Blog.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
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
    private NavigationService navigationService;

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleToArticleDTO converter;

    /**
     * 管理中心首页
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(Map<String, Object> map,
                              HttpSession session) {
        User user = getUser(session);
        map.put("user", user);
        map.put("title", "信息管理");
        return new ModelAndView("manage/manage/Index", map);
    }

    /**
     * 文章分类管理页面
     * @param map
     * @return
     */
    @GetMapping("/category")
    public ModelAndView category(Map<String, Object> map,
                                 HttpSession session) {
        User user = getUser(session);
        map.put("user", user);
        List<Category> categoryList = categoryService.getAllCategory();
        map.put("title", "分类管理");
        map.put("categoryList", categoryList);
        return new ModelAndView("manage/manage/Category", map);
    }

    /**
     * 文章管理页面
     * @param map
     * @return
     */
    @GetMapping("/article")
    public ModelAndView article(Map<String, Object> map,
                                @RequestParam(value = "page", defaultValue = "1") Integer page,
                                @RequestParam(value = "size", defaultValue = "10") Integer size,
                                HttpSession session) {
        User user = getUser(session);
        map.put("user", user);
        int maxPage = articleService.getArticleCount() / size + 1;
        page = BasicUtil.getPage(page, maxPage);
        List<Article> articleList = articleService.getAllArticles(page, size);
        List<ArticleDTO> articleDTOList = converter.convert(articleList);
        map.put("title", "管理文章");
        map.put("articleList", articleDTOList);
        map.put("currentPage", page);
        map.put("maxPage", maxPage);
        return new ModelAndView("manage/manage/Article", map);
    }

    /**
     * 添加文章页面
     * @param map
     * @return
     */
    @GetMapping("/article/add")
    public ModelAndView addArticle(Map<String, Object> map,
                                   HttpSession session) {
        User user = getUser(session);
        map.put("user", user);
        List<Category> categoryList = categoryService.getAvailableCategory();
        map.put("title", "新增文章");
        map.put("categoryList", categoryList);
        return new ModelAndView("manage/manage/AddArticle", map);
    }

    /**
     * 查看某一篇文章
     * @param map
     * @param id
     * @return
     */
    @GetMapping("/article/{id}")
    public ModelAndView showArticle(Map<String, Object> map,
                                    @PathVariable("id") Integer id,
                                    HttpSession session) {
        User user = getUser(session);
        map.put("user", user);
        Article article = articleService.getArticleByPrimaryKey(id);
        ArticleDTO articleDTO = converter.convert(article);
        map.put("title", article.getTitle());
        map.put("article", articleDTO);
        return new ModelAndView("manage/manage/ShowArticle", map);
    }

    /**
     * 编辑某一篇文章
     * @param map
     * @param id
     * @return
     */
    @GetMapping("/article/edit/{id}")
    public ModelAndView editArticle(Map<String, Object> map,
                                    @PathVariable("id") Integer id,
                                    HttpSession session) {
        User user = getUser(session);
        map.put("user", user);
        Article article = articleService.getArticleByPrimaryKey(id);
        List<Category> categoryList = categoryService.getAvailableCategory();
        map.put("title", article.getTitle());
        map.put("article", article);
        map.put("categoryList", categoryList);
        return new ModelAndView("manage/manage/EditArticle", map);
    }

    /**
     * 导航管理页面
     * @param map
     * @return
     */
    @GetMapping("/navigation")
    public ModelAndView navigation(Map<String, Object> map,
                                   HttpSession session) {
        User user = getUser(session);
        map.put("user", user);
        List<Navigation> navigationList = navigationService.getAllNavigation();
        map.put("title", "导航管理");
        map.put("navigationList", navigationList);
        return new ModelAndView("manage/manage/Navigation", map);
    }

    @GetMapping("/user")
    public ModelAndView user(Map<String, Object> map,
                             HttpSession session) {
        User user = getUser(session);
        map.put("title", "用户信息管理");
        map.put("user", user);
        return new ModelAndView("manage/manage/User", map);
    }

    /**
     * 根据Session中的值获取用户信息
     * @param session
     * @return
     */
    private User getUser(HttpSession session) {
        Integer id = (Integer) session.getAttribute(Const.USER_ID);
        return userService.getUser(id);
    }



}
