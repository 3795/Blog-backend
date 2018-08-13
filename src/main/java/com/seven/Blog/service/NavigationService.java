package com.seven.Blog.service;

import com.seven.Blog.pojo.Navigation;
import com.seven.Blog.response.ServerResponse;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 导航功能Service层
 * Created At 2018/08/10
 */
public interface NavigationService {

    List<Navigation> getAllNavigation();

    List<Navigation> getAvailableNavigation();

    ServerResponse addNavigation(String name, Integer priority, String link, Integer status);

    ServerResponse updateNavigation(Navigation navigation);

    ServerResponse deleteNavigation(Integer id);

    ServerResponse changeCategoryStatus(Integer id);
}
