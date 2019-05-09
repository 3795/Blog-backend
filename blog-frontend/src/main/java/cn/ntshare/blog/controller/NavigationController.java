package cn.ntshare.blog.controller;

import cn.ntshare.blog.dto.NavigationDTO;
import cn.ntshare.blog.service.NavigationService;
import cn.ntshare.blog.vo.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/navigation")
public class NavigationController {

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
