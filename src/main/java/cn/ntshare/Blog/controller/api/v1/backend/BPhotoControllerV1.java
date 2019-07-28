package cn.ntshare.Blog.controller.api.v1.backend;

import cn.ntshare.Blog.enums.ResponseCodeEnum;
import cn.ntshare.Blog.pojo.Photo;
import cn.ntshare.Blog.service.PhotoService;
import cn.ntshare.Blog.vo.ServerResponse;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created By Q.Hao
 * Description:
 * Created At 2019/7/28
 */
@RestController
@RequestMapping("/backend/photo")
public class BPhotoControllerV1 {

    @Autowired
    private PhotoService photoService;

    @PostMapping
    public ServerResponse create(@RequestParam(value = "title") String title,
                                 @RequestParam(value = "img") String img,
                                 @RequestParam(value = "photoTagId") Integer photoTagId,
                                 @RequestParam(value = "status") Integer status) {
        Photo photo = new Photo(title, img, photoTagId, status);
        photoService.insert(photo);
        return ServerResponse.success(ResponseCodeEnum.INSERT_SUCCESS);
    }

    @GetMapping
    public ServerResponse selectAll(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        PageInfo pageInfo = photoService.selectAll(pageNum, pageSize);
        return ServerResponse.success(pageInfo);
    }

    @PatchMapping("/status")
    public ServerResponse updateStatus(@RequestParam(value = "id") Integer id) {
        photoService.updateStatus(id);
        return ServerResponse.success(ResponseCodeEnum.UPDATE_SUCCESS);
    }

    @DeleteMapping("/{id}")
    public ServerResponse delete(@PathVariable(value = "id") Integer id) {
        photoService.delete(id);
        return ServerResponse.success(ResponseCodeEnum.DELETE_SUCCESS);
    }
}
