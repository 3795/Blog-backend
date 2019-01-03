package cn.ntshare.Blog.service.impl;

import cn.ntshare.Blog.Exception.SystemException;
import cn.ntshare.Blog.dao.NavigationMapper;
import cn.ntshare.Blog.dto.NavigationDTO;
import cn.ntshare.Blog.enums.ResponseCodeEnum;
import cn.ntshare.Blog.pojo.Navigation;
import cn.ntshare.Blog.service.NavigationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Override
    public PageInfo selectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<NavigationDTO> navigationDTOList = navigationMapper.selectAll();
        return new PageInfo<>(navigationDTOList);
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
        return new PageInfo<>(navigationDTOList);
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

    @Override
    public List<NavigationDTO> selectItem() {
        return navigationMapper.selectItem();
    }
}
