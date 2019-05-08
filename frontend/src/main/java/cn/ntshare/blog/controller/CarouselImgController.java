package cn.ntshare.blog.controller;

import cn.ntshare.blog.enums.CommonStatusEnum;
import cn.ntshare.blog.pojo.CarouselImg;
import cn.ntshare.blog.service.CarouselImgService;
import cn.ntshare.blog.vo.ServerResponse;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/carouselImg")
public class CarouselImgController {
    @Autowired
    private CarouselImgService carouselImgService;

    @GetMapping
    public ServerResponse queryCarouselImg() {
        PageInfo pageInfo = carouselImgService.queryCarouselImgByStatus(CommonStatusEnum.ON.getCode(), 1, 5);
        List<CarouselImg> list = pageInfo.getList();
        return ServerResponse.success(list);
    }
}
