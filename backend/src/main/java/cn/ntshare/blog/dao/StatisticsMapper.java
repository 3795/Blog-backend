package cn.ntshare.blog.dao;

import cn.ntshare.blog.pojo.Statistics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    /**
     * 查询所有的访问量记录
     * @return
     */
    List<Statistics> query();

    /**
     * 新增一条记录
     * @param statistics
     * @return
     */
    int insert(Statistics statistics);

    /**
     * 增加每日访问量数据
     * @param today      当天日期
     * @return
     */
    int increaseDailyViews(@Param("today") String today);

    /**
     * 根据某一天的日期来查询访问量
     * @param day       年-月-日，如：2019-01-01
     * @return
     */
    Statistics queryDailyViews(@Param("day") String day);

    /**
     * 增加月访问量
     * @param month
     * @param views
     * @return
     */
    int increaseMonthlyViews(@Param("month") String month,
                             @Param("views") Integer views);

    /**
     * 查询某一个月的月访问量
     * @param currentMonth      年-月，如：2019-01
     * @return
     */
    Statistics queryMonthViews(@Param("month") String currentMonth);
}
