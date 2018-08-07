package com.seven.Blog.controller.manage;

import com.seven.Blog.pojo.Category;
import com.seven.Blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
