package com.seven.Blog.controller.api.v1.frontend;

import com.seven.Blog.dto.UserDTO;
import com.seven.Blog.service.UserService;
import com.seven.Blog.vo.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By Seven.wk
 * Description: 用户信息接口
 * Created At 2018/11/15
 */
@RestController
@RequestMapping("/user")
public class FUserControllerV1 {

    @Autowired
    private UserService userService;

    private final Integer userId = 1;

    /**
     * 获取用户的基本信息
     * @return
     */
    @GetMapping
    public ServerResponse getUser() {
        UserDTO userDTO = userService.queryUserById(userId);
        return ServerResponse.success(userDTO);
    }
}
