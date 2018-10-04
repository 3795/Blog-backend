package com.seven.Blog.controller.api.index;

import com.seven.Blog.convert.NavigationToNavigationDTO;
import com.seven.Blog.dto.NavigationDTO;
import com.seven.Blog.pojo.Navigation;
import com.seven.Blog.service.NavigationService;
import com.seven.Blog.vo.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By Seven.wk
 * Description: 导航服务接口
 * Created At 2018/10/04
 */
@RestController
@RequestMapping("/api/index/navigation")
public class ANavigationController {

    @Autowired
    private NavigationService navigationService;

    @GetMapping
    public ServerResponse getNavigation() {
        List<NavigationDTO> navigationDTOList = new ArrayList<>();
        List<Navigation> navigationList = navigationService.getAvailableNavigation();
        navigationDTOList = NavigationToNavigationDTO.convert(navigationList);
        return ServerResponse.success(navigationDTOList);
    }
}
