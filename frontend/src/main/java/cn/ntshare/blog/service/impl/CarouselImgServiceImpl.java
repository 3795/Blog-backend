package cn.ntshare.blog.service.impl;

import cn.ntshare.blog.dao.CarouselImgMapper;
import cn.ntshare.blog.pojo.CarouselImg;
import cn.ntshare.blog.service.CarouselImgService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

}
