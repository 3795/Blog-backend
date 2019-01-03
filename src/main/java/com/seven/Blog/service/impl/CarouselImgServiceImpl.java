package com.seven.Blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seven.Blog.Exception.SystemException;
import com.seven.Blog.dao.CarouselImgMapper;
import com.seven.Blog.enums.ResponseCodeEnum;
import com.seven.Blog.pojo.CarouselImg;
import com.seven.Blog.service.CarouselImgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 实现层
 * Created At 2019/01/03
 */
@Service
@Slf4j
public class CarouselImgServiceImpl implements CarouselImgService {

    @Autowired
    private CarouselImgMapper carouselImgMapper;

    @Override
    public PageInfo queryCarouselImgByStatus(Integer status, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CarouselImg> list = carouselImgMapper.queryCarouselImgByStatus(status);
        return new PageInfo<>(list);
    }

    @Override
    public boolean insertCarouselImg(CarouselImg carouselImg) {
        int result = carouselImgMapper.insertCarouselImg(carouselImg);

        if (result != 1) {
            log.warn("insert carousel_img error!");
            throw new SystemException(ResponseCodeEnum.INSERT_FAILED);
        }

        return true;
    }

    @Override
    public boolean deleteCarouselImg(Integer id) {
        int result = carouselImgMapper.deleteCarouselImgById(id);

        if (result != 1) {
            log.warn("delete carousel_img error!");
            throw new SystemException(ResponseCodeEnum.DELETE_FAILED);
        }

        return true;
    }
}
