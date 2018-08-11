package com.seven.Blog.controller.api.manage;

import com.seven.Blog.response.ServerResponse;
import com.seven.Blog.service.UserService;
import com.seven.Blog.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created By Seven.wk
 * Description: 用户信息管理API
 * Created At 2018/08/11
 */
@RestController
@RequestMapping("/api/manage/user")
public class IUserController {

    @Autowired
    private UserService userService;

    /**
     * 更新用户的个人信息
     * @param username
     * @param avatar
     * @param session
     * @return
     */
    @PutMapping("")
    public ServerResponse updateUser(@RequestParam("username") String username,
                                     @RequestParam("avatar") String avatar,
                                     HttpSession session) {
        Integer id = (Integer) session.getAttribute(Const.USER_ID);
        return userService.updateUser(id, username, avatar);
    }

    /**
     * 用户退出登录
     * @param session
     * @return
     */
    @GetMapping("logout")
    public ServerResponse logout(HttpSession session) {
        session.setAttribute(Const.USER_ID, null);
        return ServerResponse.success("登出成功");
    }

}
