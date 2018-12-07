package com.seven.Blog.controller.api.v1.frontend;

import com.github.pagehelper.PageInfo;
import com.seven.Blog.dto.CategoryInfo;
import com.seven.Blog.service.ArticleService;
import com.seven.Blog.service.CategoryService;
import com.seven.Blog.vo.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created By Seven.wk
 * Description: 分类信息接口
 * Created At 2018/11/15
 */
@RestController
@RequestMapping("/category")
public class FCategoryControllerV1 {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    /**
     * 获得该分类下的文章
     * @param id
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/{id}")
    public ServerResponse getArticles(@PathVariable("id") Integer id,
                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        PageInfo pageInfo = articleService.selectPublishedByCate(id, pageNum, pageSize);
        return ServerResponse.success(pageInfo);
    }

    /**
     * 获取分类及其子分类的信息
     * @param id
     * @return
     */
    @GetMapping("/children")
    public ServerResponse getChildren(@RequestParam("id") Integer id) {
        CategoryInfo categoryInfo = categoryService.selectParentAndChildren(id);
        return ServerResponse.success(categoryInfo);
    }

    /**
     * 统计分类的个数
     * @return
     */
    @GetMapping("/count")
    public ServerResponse count() {
        int result = categoryService.count();
        return ServerResponse.success(result);
    }

}
