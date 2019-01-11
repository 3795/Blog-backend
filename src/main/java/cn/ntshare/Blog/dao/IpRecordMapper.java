package cn.ntshare.Blog.dao;

import cn.ntshare.Blog.pojo.IpRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: IP记录DAO层
 * Created At 2019/01/11
 */
@Mapper
@Repository
public interface IpRecordMapper {

    List<IpRecord> query();

    IpRecord queryByIp(String ip);

    int insert(IpRecord ipRecord);

    int delete(Integer id);

    int updateStatus(Integer id);

    int increaseCount(String ip);
}
