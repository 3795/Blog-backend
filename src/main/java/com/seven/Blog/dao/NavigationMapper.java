package com.seven.Blog.dao;

import com.seven.Blog.dto.NavigationDTO;
import com.seven.Blog.pojo.Navigation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 导航功能的Mapper
 * Created At 2018/08/10
 */
@Repository
@Mapper
public interface NavigationMapper {

    int addNavigation(@Param("name") String name,
                      @Param("priority") Integer priority,
                      @Param("link") String link,
                      @Param("status") Integer status);

    List<Navigation> getAllNavigation();

    List<Navigation> getNavigationByStatus(Integer status);

    Navigation selectedByPrimaryKey(Integer key);

    int deleteNavigationByPrimaryKey(Integer key);

    int updateNavigation(Navigation navigation);

    /**
     * 获得所有导航的数量
     * @return
     */
    int getNavigationCount();

    /*-----------------------------二期新增----------------------------*/

    List<NavigationDTO> selectAll();

    NavigationDTO selectById(Integer id);

    List<NavigationDTO> selectByStatus(Integer status);

    int insert(Navigation navigation);

    int update(Navigation navigation);

    int updateStatus(@Param("id") Integer id,
                     @Param("status") Integer status);

    int delete(Integer id);
}
