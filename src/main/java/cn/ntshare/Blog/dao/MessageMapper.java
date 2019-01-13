package cn.ntshare.Blog.dao;

import cn.ntshare.Blog.pojo.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 系统消息Dao层
 * Created At 2019/01/11
 */
@Mapper
@Repository
public interface MessageMapper {

    List<Message> queryByStatus(@Param("status") Integer status);

    int insert(Message message);

    int updateStatus(Integer id);

    int delete(Integer id);

    Integer countByStatus(@Param("status") Integer status);

    int empty();
}
