package com.seven.Blog.controller.api;

import com.seven.Blog.response.ServerResponse;
import com.seven.Blog.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By Seven.wk
 * Description: 用户信息API接口
 * Created At 2018/08/06
 */
@RestController
@RequestMapping("/api/user")
public class IUserController {

    @Autowired
    private UserService userService;

    @PostMapping("/doLogin")
    public ServerResponse doLogin(@Param("account") String account,
                                  @Param("password") String password) {
        return userService.checkLoginInfo(account, password);
    }

}
