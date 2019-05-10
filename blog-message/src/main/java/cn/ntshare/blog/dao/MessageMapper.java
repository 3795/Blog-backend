package cn.ntshare.blog.dao;

import cn.ntshare.blog.pojo.Message;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created By Seven.wk
 * Description: 系统消息Dao层
 * Created At 2019/01/11
 */
@Mapper
@Repository
public interface MessageMapper {

    int insert(Message message);

}
