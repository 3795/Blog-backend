package com.seven.Blog.controller.api.v1.frontend;

import com.seven.Blog.convert.NavigationToNavigationDTO;
import com.seven.Blog.dto.NavigationDTO;
import com.seven.Blog.pojo.Navigation;
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
@RequestMapping("/blog/v1/navigation")
public class FNavigationControllerV1 {

    @Autowired
    private NavigationService navigationService;

    @GetMapping
    public ServerResponse getNavigation() {
        List<NavigationDTO> navigationDTOList;
        List<Navigation> navigationList = navigationService.getAvailableNavigation();
        navigationDTOList = NavigationToNavigationDTO.convert(navigationList);
        return ServerResponse.success(navigationDTOList);
    }

}
