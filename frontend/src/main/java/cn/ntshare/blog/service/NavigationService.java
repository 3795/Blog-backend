package cn.ntshare.blog.service;

import cn.ntshare.blog.dto.NavigationDTO;
import com.github.pagehelper.PageInfo;

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

    List<NavigationDTO> selectItem();
}
