package com.seven.Blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created By Seven.wk
 * Description: 测试所用的控制器
 * Created At 2018/08/06
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping("/index")
    public ModelAndView index(Map<String, Object> map) {
        map.put("title", "你好");
        return new ModelAndView("test/test", map);
    }
}
