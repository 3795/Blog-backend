package com.seven.Blog.controller.api.index;

import com.seven.Blog.dto.UserDTO;
import com.seven.Blog.enums.ResponseCodeEnum;
import com.seven.Blog.pojo.User;
import com.seven.Blog.vo.ServerResponse;
import com.seven.Blog.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created By Seven.wk
 * Description: 用户信息服务接口
 * Created At 2018/10/02
 */
@RestController
@RequestMapping("/api/index/user")
public class AUserController {

    @Autowired
    private UserService userService;

    private final Integer userId = 1;

    @GetMapping
    public ServerResponse getUser(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Connection, User-Agent, Cookie");

        User user = userService.getUser(userId);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return ServerResponse.success(ResponseCodeEnum.SUCCESS, userDTO);
    }

}
