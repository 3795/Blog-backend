package cn.ntshare.Blog.controller.api.v1.frontend;

import cn.ntshare.Blog.enums.CommonStatusEnum;
import cn.ntshare.Blog.pojo.CarouselImg;
import cn.ntshare.Blog.service.CarouselImgService;
import cn.ntshare.Blog.vo.ServerResponse;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 前台轮播图接口
 * Created At 2019/01/03
 */
@RestController
@RequestMapping("/carouselImg")
@Api(tags = "前台轮播图接口")
public class FCarouselImgControllerV1 {

    @Autowired
    private CarouselImgService carouselImgService;

    @GetMapping
    @ApiOperation("查询所有轮播图")
    public ServerResponse queryCarouselImg() {
        PageInfo pageInfo = carouselImgService.queryCarouselImgByStatus(CommonStatusEnum.ON.getCode(), 1, 5);
        List<CarouselImg> list = pageInfo.getList();
        return ServerResponse.success(list);
    }
}
