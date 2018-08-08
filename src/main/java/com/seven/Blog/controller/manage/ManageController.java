package com.seven.Blog.controller.manage;

import com.seven.Blog.dto.ArticleDTO;
import com.seven.Blog.pojo.Article;
import com.seven.Blog.pojo.Category;
import com.seven.Blog.response.ServerResponse;
import com.seven.Blog.service.ArticleService;
import com.seven.Blog.service.CategoryService;
import com.seven.Blog.utils.ArticleToArticleDTO;
import com.seven.Blog.utils.FileNameUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    public ModelAndView addArticle(Map<String, String> map) {
        map.put("title", "新增文章");
        return new ModelAndView("manage/manage/editArticle", map);
    }

    @PostMapping("/upload")
    @ResponseBody
    public ServerResponse upload(@RequestParam("file") MultipartFile file) {
        if(!file.isEmpty()) {
            try {
                String path = "./src/main/resources/static";
                path += FileNameUtil.getFileName(file.getOriginalFilename());
                File destination = new File(path);
                FileUtils.copyInputStreamToFile(file.getInputStream(), destination);
                return ServerResponse.success("上传成功");
            } catch (IOException e) {
                return ServerResponse.error("上传失败" + e.getMessage());
            }
        }
        return ServerResponse.error("文件不能为空");
    }

    public static void main(String[] args) {
//        1533736943399
//        1533736958304
//        1533737059556
        String mi = "1533737059556";
        mi = mi.substring(0, 11);
        System.out.println(mi);
    }
}
