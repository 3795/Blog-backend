package cn.ntshare.blog.dao;

import cn.ntshare.blog.pojo.ImgRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 图片上传记录接口
 * Created At 2019/01/07
 */
@Mapper
@Repository
public interface ImgRecordMapper {

    int insert(String img);

}
