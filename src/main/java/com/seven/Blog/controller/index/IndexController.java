package com.seven.Blog.controller.index;

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
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private NavigationService navigationService;

    @Autowired
    private ArticleToArticleDTO articleToArticleDTO;

    private Integer size = 5;       //设置每页默认显示5条文章

    private Integer userId = 1;     //博主的id默认为1

    /**
     * 首页展示内容
     * @param map
     * @param page
     * @return
     */
    @GetMapping("")
    public ModelAndView index(Map<String, Object> map,
                              @RequestParam(value = "page", defaultValue = "1") Integer page) {
        Integer maxPage = (int) Math.ceil((float)articleService.getArticleCountByStatus(Const.ArticleStatus.PUBLISHED.getCode()) / size);
        page = BasicUtil.getPage(page, maxPage);
        List<Article> articleList = articleService.getAllPublishedArticle(page, size);
        List<ArticleDTO> articleDTOList = articleToArticleDTO.convert(articleList);
        map.put("title", "NTShare");
        map.put("articleList", articleDTOList);
        map.put("user", getUser());
        map.put("navigationList", getNavigation());
        map.put("currentPage", page);
        map.put("maxPage", maxPage);
        map.put("url", "?page=");
        return new ModelAndView("index/index/TemplateA", map);
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
        Article article = articleService.getPublishedArticleByPrimaryKey(id);
        if(article == null)
            return new ModelAndView("redirect:/404");
        ArticleDTO articleDTO = articleToArticleDTO.convert(article);
        map.put("title", articleDTO.getTitle());
        map.put("article", articleDTO);
        map.put("user", getUser());
        map.put("navigationList", getNavigation());
        return new ModelAndView("/index/index/Article", map);
    }

    /**
     * 展示分类下的文章
     * @param map
     * @param id
     * @return
     */
    @GetMapping("/category/{id}")
    public ModelAndView category(Map<String, Object> map,
                                 @PathVariable("id") Integer id,
                                 @RequestParam(value = "page", defaultValue = "1") Integer page) {
        Category category = categoryService.getCategoryById(id);
        if(category == null)
            return new ModelAndView("redirect:/404");

        map.put("user", getUser());
        map.put("navigationList", getNavigation());
        map.put("title", category.getName());
        map.put("category", category);

        //获取分页的相关数据
        int maxPage = (int) Math.ceil((float)getArticleCountByCategory(category, 0) / size);
        page = BasicUtil.getPage(page, maxPage);

        map.put("currentPage", page);
        map.put("maxPage", maxPage);
        map.put("url", "/category/" + id + "?page=");

        if(category.getParentId() == 0) {       //一级分类
            String sql = "";
            List<Category> categoryList = categoryService.getChildCategory(category.getId());
            for(Category categoryItem : categoryList) {
                sql = sql + categoryItem.getId() + ",";
            }
            List<Article> articleList = articleService.getPublishedArticleByCategoryIds(sql, page, size);
            List<ArticleDTO> articleDTOList = articleToArticleDTO.convert(articleList);
            map.put("articleList", articleDTOList);
            map.put("categoryList", categoryList);
            return new ModelAndView("index/index/TemplateB", map);
        } else {        //二级分类
            List<Article> articleList = articleService.getPublishedArticleByCategoryId(category.getId(), page, size);
            List<ArticleDTO> articleDTOList = articleToArticleDTO.convert(articleList);
            map.put("articleList", articleDTOList);
            return new ModelAndView("index/index/TemplateA", map);
        }
    }

    /**
     * 文章搜索功能
     * @param map
     * @param keywords
     * @param page
     * @return
     */
    @GetMapping("/search")
    public ModelAndView search(Map<String, Object> map,
                               @RequestParam(value = "keywords", defaultValue = "") String keywords,
                               @RequestParam(value = "page", defaultValue = "1") Integer page) {
        map.put("user", getUser());
        map.put("navigationList", getNavigation());
        map.put("title", "搜索");
        map.put("keywords", keywords);
        map.put("url", "/search?keywords=" + keywords + "&page=");

        //获取分页的相关数据
        int maxPage = (int) Math.ceil((float)articleService.getArticleCountByKeywords(keywords) / size);
        page = BasicUtil.getPage(page, maxPage);
        map.put("currentPage", page);
        map.put("maxPage", maxPage);

        List<Article> articleList = articleService.getPublishedArticleByKeywords(keywords, page, size);
        List<ArticleDTO> articleDTOList = articleToArticleDTO.convert(articleList);
        map.put("articleList", articleDTOList);
        return new ModelAndView("index/index/Search", map);
    }

    /**
     * 404页面
     * @param map
     * @return
     */
    @GetMapping("/404")
    public ModelAndView notFound(Map<String, Object> map) {
        map.put("title", "Page Not Found");
        map.put("user", getUser());
        map.put("navigationList", getNavigation());
        return new ModelAndView("index/error/404", map);
    }


    /**
     * 获取博主的信息
     * @return
     */
    private User getUser() {
        return userService.getUser(userId);
    }

    /**
     * 获得启用的导航
     * @return
     */
    private List<Navigation> getNavigation() {
        return navigationService.getAvailableNavigation();
    }

    /**
     * 获得文章数
     * @param category
     * @param count
     * @return
     */
    private Integer getArticleCountByCategory(Category category, int count) {
        if(category.getParentId() == 0) {
            List<Category> categoryList = categoryService.getChildCategory(category.getId());
            for(Category categoryItem : categoryList) {
                count = getArticleCountByCategory(categoryItem, count);
            }
            return count;
        } else {
            count += articleService.getArticleCountByCategoryId(category.getId());
            return count;
        }
    }

}
