package com.seven.Blog.controller.api.v1.frontend;

import com.seven.Blog.convert.ArticleToArticleCardDTO;
import com.seven.Blog.convert.CategoryToCategoryDTO;
import com.seven.Blog.dto.ArticleCardDTO;
import com.seven.Blog.dto.ArticleCardsDTO;
import com.seven.Blog.dto.CategoryDTO;
import com.seven.Blog.dto.CategoryInfo;
import com.seven.Blog.enums.ResponseCodeEnum;
import com.seven.Blog.pojo.Article;
import com.seven.Blog.pojo.Category;
import com.seven.Blog.service.ArticleService;
import com.seven.Blog.service.CategoryService;
import com.seven.Blog.util.BasicUtil;
import com.seven.Blog.vo.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 分类信息接口
 * Created At 2018/11/15
 */
@RestController
@RequestMapping("/blog/v1/category")
public class FCategoryControllerV1 {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleToArticleCardDTO articleToArticleCardDTO;

    private Integer size = 5;

    /**
     * 获得该分类下的文章
     * @param id
     * @param page
     * @return
     */
    @GetMapping("/{id}")
    public ServerResponse category(@PathVariable("id") Integer id,
                                   @RequestParam(value = "page", defaultValue = "1") Integer page) {
        Category category = categoryService.getCategoryById(id);
        if(category == null)
            return ServerResponse.error(ResponseCodeEnum.PAGE_NOT_FOUND);

        //获取分页的相关数据
        int maxPage = (int) Math.ceil((float)getArticleCountByCategory(category, 0) / size);
        page = BasicUtil.getPage(page, maxPage);

        if(category.getParentId() == 0) {       //一级分类
            String sql = "";
            List<Category> categoryList = categoryService.getChildCategory(category.getId());
            for(Category categoryItem : categoryList) {
                sql = sql + categoryItem.getId() + ",";
            }
            List<Article> articleList = articleService.getPublishedArticleByCategoryIds(sql, page, size);
            List<ArticleCardDTO> articleCardDTOList = articleToArticleCardDTO.convert(articleList);
            ArticleCardsDTO articleCardsDTO = new ArticleCardsDTO(articleCardDTOList, page, maxPage);
            return ServerResponse.success(articleCardsDTO);
        } else {        //二级分类
            List<Article> articleList = articleService.getPublishedArticleByCategoryId(category.getId(), page, size);
            List<ArticleCardDTO> articleCardDTOList = articleToArticleCardDTO.convert(articleList);
            ArticleCardsDTO articleCardsDTO = new ArticleCardsDTO(articleCardDTOList, page, maxPage);
            return ServerResponse.success(articleCardsDTO);
        }

    }


    @GetMapping("/{id}/children")
    public ServerResponse childrenCategories(@PathVariable("id") Integer id) {
        CategoryInfo categoryInfo;
        Category category = categoryService.getCategoryById(id);

        if(category == null)
            return ServerResponse.error(ResponseCodeEnum.PAGE_NOT_FOUND);

        if(category.getParentId() == 0) {       //一级分类
            List<Category> categoryList = categoryService.getChildCategory(category.getId());
            List<CategoryDTO> categoryDtos = CategoryToCategoryDTO.convert(categoryList);
            categoryInfo = new CategoryInfo(category.getName(), categoryDtos);
        }
        else
            categoryInfo = new CategoryInfo(category.getName());

        return ServerResponse.success(categoryInfo);
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
