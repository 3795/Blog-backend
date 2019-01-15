package cn.ntshare.Blog.dao;

import cn.ntshare.Blog.pojo.Statistics;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 数据统计Mapper
 * Created At 2019/01/15
 */
@Mapper
@Repository
public interface StatisticsMapper {

    List<Statistics> query();

    int insert(Statistics statistics);
}
