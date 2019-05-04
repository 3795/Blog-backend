package cn.ntshare.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By Q.Hao
 * Description: 文章控制器
 * Created At 2019/4/28
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @GetMapping
    public String t() {
        return "测试成功";
    }
}
