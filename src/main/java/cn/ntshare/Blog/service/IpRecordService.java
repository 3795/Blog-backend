package cn.ntshare.Blog.service;

import com.github.pagehelper.PageInfo;

/**
 * Created By Seven.wk
 * Description: IP记录服务
 * Created At 2019/01/11
 */
public interface IpRecordService {

    /**
     * 分页查询记录
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo query(int pageNum, int pageSize);

    /**
     * 该ip地址是否在记录中
     * @param ip
     * @return
     */
    Boolean isExists(String ip);

    /**
     * 插入一条IP记录
     * @param ip
     */
    void insert(String ip);

    /**
     * 删除一条IP记录
     * @param id
     */
    void delete(Integer id);

    /**
     * 更改记录状态
     * @param id
     */
    void updateStatus(Integer id);

    /**
     * 增加该IP的访问次数
     * @param ip
     */
    void increaseCount(String ip);

}
