package com.seven.Blog.service.impl;

import com.seven.Blog.dao.NavigationMapper;
import com.seven.Blog.pojo.Category;
import com.seven.Blog.pojo.Navigation;
import com.seven.Blog.response.ServerResponse;
import com.seven.Blog.service.NavigationService;
import com.seven.Blog.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 导航功能Service实现
 * Created At 2018/08/10
 */
@Service
public class NavigationServiceImpl implements NavigationService {

    @Autowired
    private NavigationMapper navigationMapper;

    public List<Navigation> getAllNavigation() {
        return navigationMapper.getAllNavigation();
    }

    @Override
    public List<Navigation> getAvailableNavigation() {
        return navigationMapper.getNavigationByStatus(Const.NavigationStatus.ABLE.getCode());
    }

    @Override
    public ServerResponse addNavigation(String name, Integer priority, String link, Integer status) {
        int result = navigationMapper.addNavigation(name, priority, link, status);
        if(result == 1)
            return ServerResponse.success("添加导航成功");
        return ServerResponse.error("添加导航失败");
    }

    @Override
    public ServerResponse updateNavigation(Navigation navigation) {
        int result = navigationMapper.updateNavigation(navigation);
        if(result == 1)
            return ServerResponse.success("修改导航成功");
        return ServerResponse.error("修改导航失败");
    }

    @Override
    public ServerResponse deleteNavigation(Integer id) {
        int result = navigationMapper.deleteNavigationByPrimaryKey(id);
        if(result == 1)
            return ServerResponse.success("删除导航成功");
        return ServerResponse.error("删除导航失败");
    }

    @Override
    public ServerResponse changeCategoryStatus(Integer id) {
        Navigation navigation = navigationMapper.selectedByPrimaryKey(id);
        if(navigation.getStatus() == Const.NavigationStatus.ABLE.getCode())
            navigation.setStatus(Const.NavigationStatus.DISABLE.getCode());
        else
            navigation.setStatus(Const.NavigationStatus.ABLE.getCode());
        int result = navigationMapper.updateNavigation(navigation);
        if(result == 1)
            return ServerResponse.success("更改状态成功");
        return ServerResponse.error("更改状态失败");
    }
}
