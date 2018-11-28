package com.seven.Blog.service;

import com.github.pagehelper.PageInfo;
import com.seven.Blog.dto.NavigationDTO;
import com.seven.Blog.pojo.Navigation;
import com.seven.Blog.vo.ServerResponse;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 导航功能Service层
 * Created At 2018/08/10
 */
public interface NavigationService {

    PageInfo selectAll(int pageNum, int pageSize);

    NavigationDTO selectById(Integer id);

    PageInfo selectByStatus(int status, int pageNum, int pageSize);

    Boolean insert(Navigation navigation);

    Boolean update(Navigation navigation);

    Boolean updateStatus(Integer id, Integer status);

    Boolean delete(Integer id);
}
