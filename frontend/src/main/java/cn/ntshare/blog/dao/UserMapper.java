package cn.ntshare.blog.dao;

import cn.ntshare.blog.dto.UserDTO;
import cn.ntshare.blog.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created By Seven.wk
 * Description: 用户信息接口
 * Created At 2018/08/06
 */
@Repository
@Mapper
public interface UserMapper {

    UserDTO selectByAccountAndPassword(@Param("account") String account,
                                       @Param("password") String password);

    UserDTO selectById(Integer id);

    int queryByPassword(@Param("id") Integer id,
                        @Param("password") String password);

    int updateInfo(User user);

    int updateAvatar(@Param("id") Integer id,
                     @Param("avatar") String avatar);

    int updatePassword(@Param("id") Integer id,
                       @Param("password") String password);


}
