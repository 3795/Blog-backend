package com.seven.Blog.controller.api.v1.manage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By Seven.wk
 * Description: 文章管理控制器
 * Created At 2018/11/11
 */
@RestController
@RequestMapping("/blog/v1/article")
public class MArticleControllerV1 {

    @GetMapping
    public String test() {
        return "我是测试";
    }
}
