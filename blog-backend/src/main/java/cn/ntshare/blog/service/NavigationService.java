package cn.ntshare.blog.service;

import cn.ntshare.blog.dto.NavigationDTO;
import cn.ntshare.blog.pojo.Navigation;
import com.github.pagehelper.PageInfo;

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
