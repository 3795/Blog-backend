package com.seven.Blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seven.Blog.Exception.SystemException;
import com.seven.Blog.dao.NavigationMapper;
import com.seven.Blog.dto.NavigationDTO;
import com.seven.Blog.enums.ResponseCodeEnum;
import com.seven.Blog.pojo.Navigation;
import com.seven.Blog.service.NavigationService;
import com.seven.Blog.util.ConstUtil;
import com.seven.Blog.vo.ServerResponse;
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
        return navigationMapper.getNavigationByStatus(ConstUtil.NavigationStatus.ABLE.getCode());
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
        if(navigation.getStatus() == ConstUtil.NavigationStatus.ABLE.getCode())
            navigation.setStatus(ConstUtil.NavigationStatus.DISABLE.getCode());
        else
            navigation.setStatus(ConstUtil.NavigationStatus.ABLE.getCode());
        int result = navigationMapper.updateNavigation(navigation);
        if(result == 1)
            return ServerResponse.success("更改状态成功");
        return ServerResponse.error("更改状态失败");
    }


    /*---------------------------二期新增---------------------------------------*/

    @Override
    public PageInfo selectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<NavigationDTO> navigationDTOList = navigationMapper.selectAll();
        return new PageInfo(navigationDTOList);
    }

    @Override
    public NavigationDTO selectById(Integer id) {
        NavigationDTO navigationDTO = navigationMapper.selectById(id);
        if (navigationDTO == null) {
            throw new SystemException(ResponseCodeEnum.PAGE_NOT_FOUND);
        }
        return navigationDTO;
    }

    @Override
    public PageInfo selectByStatus(int status, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<NavigationDTO> navigationDTOList = navigationMapper.selectByStatus(status);
        return new PageInfo(navigationDTOList);
    }

    @Override
    public Boolean insert(Navigation navigation) {
        int result = navigationMapper.insert(navigation);
        if (result != 1) {
            throw new SystemException(ResponseCodeEnum.INSERT_FAILED);
        }
        return true;
    }

    @Override
    public Boolean update(Navigation navigation) {
        int result = navigationMapper.update(navigation);
        if (result != 1) {
            throw new SystemException(ResponseCodeEnum.INSERT_FAILED);
        }
        return true;
    }

    @Override
    public Boolean updateStatus(Integer id, Integer status) {
        int result = navigationMapper.updateStatus(id, status);
        if (result != 1) {
            throw new SystemException(ResponseCodeEnum.INSERT_FAILED);
        }
        return true;
    }

    @Override
    public Boolean delete(Integer id) {
        int result = navigationMapper.delete(id);
        if (result != 1) {
            throw new SystemException(ResponseCodeEnum.INSERT_FAILED);
        }
        return true;
    }
}
