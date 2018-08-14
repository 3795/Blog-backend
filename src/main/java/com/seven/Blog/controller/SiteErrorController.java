package com.seven.Blog.controller;

import com.seven.Blog.pojo.Navigation;
import com.seven.Blog.pojo.User;
import com.seven.Blog.service.NavigationService;
import com.seven.Blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created By Seven.wk
 * Description: 系统对于出现的error显示对应的页面
 * Created At 2018/08/14
 */
@Controller
public class SiteErrorController implements ErrorController {

    @Autowired
    private UserService userService;

    @Autowired
    private NavigationService navigationService;

    private Integer userId = 1;     //博主的id默认为1

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request,
                                    Map<String, Object> map) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        map.put("user", getUser());
        map.put("navigationList", getNavigation());
        if(statusCode == 404) {
            map.put("title", "Page Not Found");
            return new ModelAndView("index/error/404", map);
        } else {
            map.put("title", "System Error");
            return new ModelAndView("index/error/500", map);
        }
    }

    @Override
    public String getErrorPath() {
        return "/error";
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
}
