package com.seven.Blog.controller.manage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created By Seven.wk
 * Description: 登录控制器
 * Created At 2018/08/07
 */
@Controller
@RequestMapping("/manage")
public class AuthController {

    @RequestMapping("")
    public String index() {
        return "redirect:/manage/login";
    }

    @GetMapping("/login")
    public ModelAndView login(Map<String, String> map) {
        map.put("title", "请登录");
        return new ModelAndView("manage/auth/Login", map);
    }

}
