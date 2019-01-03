package cn.ntshare.Blog.dao;

import cn.ntshare.Blog.pojo.CarouselImg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created By Seven.wk
 * Description: 轮播图片DAO
 * Created At 2019/01/03
 */
@Mapper
@Repository
public interface CarouselImgMapper {

    List<CarouselImg> queryCarouselImgByStatus(@Param("status") Integer status);

    int insertCarouselImg(CarouselImg img);

    int updateCarouselImg(CarouselImg img);

    int deleteCarouselImgById(Integer id);

}
