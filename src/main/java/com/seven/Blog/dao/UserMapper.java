package com.seven.Blog.dao;

import com.seven.Blog.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created By Seven.wk
 * Description: 用户信息接口
 * Created At 2018/08/06
 */
@Mapper
public interface UserMapper {

    /**
     * 根据用户输入的账号和密码查找用户
     * @param account       账号
     * @param password      密码
     * @return      用户信息
     */
    User selectByAccountAndPassword(@Param("account") String account,
                                    @Param("password") String password);

    User selectedUserByPrimaryKey(Integer id);

    int updateUser(@Param("id") Integer id,
                   @Param("username") String username,
                   @Param("avatar") String avatar);


}
