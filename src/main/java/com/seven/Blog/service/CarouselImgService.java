package com.seven.Blog.service;

import com.github.pagehelper.PageInfo;
import com.seven.Blog.pojo.CarouselImg;

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
