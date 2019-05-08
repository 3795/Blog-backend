package cn.ntshare.blog.controller;

import cn.ntshare.blog.dto.UserDTO;
import cn.ntshare.blog.service.UserService;
import cn.ntshare.blog.vo.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

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
