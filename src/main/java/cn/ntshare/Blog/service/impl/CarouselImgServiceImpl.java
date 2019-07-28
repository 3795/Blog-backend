package cn.ntshare.Blog.service.impl;

import cn.ntshare.Blog.exception.SystemException;
import cn.ntshare.Blog.dao.CarouselImgMapper;
import cn.ntshare.Blog.enums.ResponseCodeEnum;
import cn.ntshare.Blog.pojo.CarouselImg;
import cn.ntshare.Blog.service.CarouselImgService;
import cn.ntshare.Blog.service.ImgRecordService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private ImgRecordService imgRecordService;

    @Override
    public PageInfo queryCarouselImgByStatus(Integer status, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CarouselImg> list = carouselImgMapper.queryCarouselImgByStatus(status);
        return new PageInfo<>(list);
    }

    @Override
    @Transactional
    public boolean insertCarouselImg(CarouselImg carouselImg) {
        int result = carouselImgMapper.insertCarouselImg(carouselImg);

        if (result != 1) {
            log.warn("insert carousel_img error!");
            throw new SystemException(ResponseCodeEnum.INSERT_FAILED);
        }

        // 更新图片记录
        imgRecordService.updateCarouselImgIdByImg(carouselImg.getId(), carouselImg.getImg());

        return true;
    }

    @Override
    public boolean deleteCarouselImg(Integer id) {
        int result = carouselImgMapper.deleteCarouselImgById(id);

        if (result != 1) {
            log.warn("delete carousel_img error!");
            throw new SystemException(ResponseCodeEnum.DELETE_FAILED);
        }

        // 删除对应的图片记录
        imgRecordService.deleteByCarouselImgId(id);

        return true;
    }
}
