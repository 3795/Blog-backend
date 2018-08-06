package com.seven.Blog.controller.manage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created By Seven.wk
 * Description: 用户管理控制器
 * Created At 2018/08/06
 */
@Controller
public class UserController {

    @RequestMapping("")
    public String index() {
        return "redirect:login";
    }

    @RequestMapping("/login")
    public ModelAndView login(Map<String, String> map) {
        map.put("title", "请登录");
        return new ModelAndView("manage/user/Login", map);
    }

}
