package cn.ntshare.Blog.controller.api.v1.frontend;

import cn.ntshare.Blog.dto.UserDTO;
import cn.ntshare.Blog.service.UserService;
import cn.ntshare.Blog.vo.ServerResponse;
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
