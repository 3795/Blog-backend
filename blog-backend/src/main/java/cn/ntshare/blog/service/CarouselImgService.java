package cn.ntshare.blog.service;

import cn.ntshare.blog.pojo.CarouselImg;
import com.github.pagehelper.PageInfo;

/**
 * Created By Seven.wk
 * Description: 轮播图Service
 * Created At 2019/01/03
 */
public interface CarouselImgService {

    PageInfo queryCarouselImgByStatus(Integer status, int pageNum, int pageSize);

    boolean insertCarouselImg(CarouselImg carouselImg);

    boolean deleteCarouselImg(Integer id);
}
