package cn.ntshare.Blog.service.impl;

import cn.ntshare.Blog.dao.IpRecordMapper;
import cn.ntshare.Blog.enums.CommonStatusEnum;
import cn.ntshare.Blog.enums.ResponseCodeEnum;
import cn.ntshare.Blog.exception.SystemException;
import cn.ntshare.Blog.pojo.IpRecord;
import cn.ntshare.Blog.service.IpRecordService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 实现层
 * Created At 2019/01/11
 */
@Service
@Slf4j
public class IpRecordServiceImpl implements IpRecordService {

    @Autowired
    private IpRecordMapper ipRecordMapper;

    @Override
    public PageInfo query(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<IpRecord> list = ipRecordMapper.query();
        return new PageInfo<>(list);
    }

    @Override
    public Boolean isExists(String ip) {
        IpRecord ipRecord = ipRecordMapper.queryByIp(ip);
        // 没有该记录
        if (ipRecord == null) {
            return false;
        }

        // 该IP被禁止访问
        if (ipRecord.getStatus().equals(CommonStatusEnum.OFF.getCode())) {
            log.info("已拒绝IP {} 访问", ip);
            throw new SystemException(ResponseCodeEnum.NO_ACCESS);
        }

        return true;
    }

    @Override
    public void insert(String ip) {
        int result = ipRecordMapper.insert(new IpRecord(ip));
        if (result != 1) {
            log.error("insert ip_record error");
            throw new SystemException(ResponseCodeEnum.INSERT_FAILED);
        }
    }

    @Override
    public void delete(Integer id) {
        int result = ipRecordMapper.delete(id);
        if (result != 1) {
            log.error("delete ip_record error, id = {}", id);
            throw new SystemException(ResponseCodeEnum.DELETE_FAILED);
        }
    }

    @Override
    public void updateStatus(Integer id) {
        int result = ipRecordMapper.updateStatus(id);
        if (result != 1) {
            log.error("update ip_record error, id = {}", id);
            throw new SystemException(ResponseCodeEnum.UPDATE_FAILED);
        }
    }

    @Override
    public void increaseCount(String ip) {
        int result = ipRecordMapper.increaseCount(ip);
        if (result != 1) {
            log.error("update ip_record error, ip = {}", ip);
            throw new SystemException(ResponseCodeEnum.UPDATE_FAILED);
        }
    }
}
