package com.seven.Blog.controller.api.v1.frontend;

import com.seven.Blog.dto.NavigationDTO;
import com.seven.Blog.service.NavigationService;
import com.seven.Blog.vo.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 导航信息接口
 * Created At 2018/11/15
 */
@RestController
@RequestMapping("/navigation")
public class FNavigationControllerV1 {

    @Autowired
    private NavigationService navigationService;

    /**
     * 获取导航链接信息
     * @return
     */
    @GetMapping
    public ServerResponse getNavigation() {
        List<NavigationDTO> list = navigationService.selectItem();
        return ServerResponse.success(list);
    }

}
