package com.seven.Blog.controller.api.v1.backend;

import com.github.pagehelper.PageInfo;
import com.seven.Blog.enums.ResponseCodeEnum;
import com.seven.Blog.form.CarouselImgForm;
import com.seven.Blog.pojo.CarouselImg;
import com.seven.Blog.service.CarouselImgService;
import com.seven.Blog.vo.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created By Seven.wk
 * Description: 轮播图控制器
 * Created At 2019/01/03
 */
@RestController
@RequestMapping("/backend/carouselImg")
@Api(tags = "后台轮播图接口")
public class BCarouselImgControllerV1 {

    @Autowired
    private CarouselImgService carouselImgService;

    @GetMapping
    @ApiOperation("分页查询所有轮播图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "图片状态", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", paramType = "query"),
    })
    public ServerResponse queryCarouselImgByStatus(@RequestParam(value = "status", required = false) Integer status,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                   @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        PageInfo pageInfo = carouselImgService.queryCarouselImgByStatus(status, pageNum, pageSize);
        return ServerResponse.success(pageInfo);
    }

    @PostMapping
    @ApiOperation("新增轮播图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "img", value = "图片地址", required = true, paramType = "query"),
            @ApiImplicitParam(name = "link", value = "图片链接地址", paramType = "query"),
    })
    public ServerResponse insertCarouselImg(@Valid CarouselImgForm form,
                                            BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.error(result.getFieldError().getDefaultMessage());
        }

        CarouselImg carouselImg = new CarouselImg(form.getImg(), form.getLink());
        carouselImgService.insertCarouselImg(carouselImg);

        return ServerResponse.success(ResponseCodeEnum.INSERT_SUCCESS);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除图片")
    @ApiImplicitParam(name = "id", value = "图片ID", required = true, paramType = "path")
    public ServerResponse deleteCarouselImg(@PathVariable("id") Integer id) {
        carouselImgService.deleteCarouselImg(id);
        return ServerResponse.success(ResponseCodeEnum.DELETE_SUCCESS);
    }
}
