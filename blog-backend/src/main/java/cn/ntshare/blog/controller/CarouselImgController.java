package cn.ntshare.blog.controller;

import cn.ntshare.blog.enums.ResponseCodeEnum;
import cn.ntshare.blog.form.CarouselImgForm;
import cn.ntshare.blog.pojo.CarouselImg;
import cn.ntshare.blog.service.CarouselImgService;
import cn.ntshare.blog.vo.ServerResponse;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/carouselImg")
public class CarouselImgController {

    @Autowired
    private CarouselImgService carouselImgService;

    @GetMapping
    public ServerResponse queryCarouselImgByStatus(@RequestParam(value = "status", required = false) Integer status,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                   @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        PageInfo pageInfo = carouselImgService.queryCarouselImgByStatus(status, pageNum, pageSize);
        return ServerResponse.success(pageInfo);
    }

    @PostMapping
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
    public ServerResponse deleteCarouselImg(@PathVariable("id") Integer id) {
        carouselImgService.deleteCarouselImg(id);
        return ServerResponse.success(ResponseCodeEnum.DELETE_SUCCESS);
    }
}
