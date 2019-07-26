package cn.ntshare.Blog.controller.api.v1.backend;

import cn.ntshare.Blog.enums.ResponseCodeEnum;
import cn.ntshare.Blog.service.PhotoTagService;
import cn.ntshare.Blog.vo.ServerResponse;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * PhotoTagController
 */
@RestController
@RequestMapping("/backend/photoTag")
public class BPhotoTagControllerV1 {

    @Autowired
    private PhotoTagService photoTagService;

    @PostMapping
    public ServerResponse create(@RequestParam(value = "name") String photoTagName) {
        photoTagService.insert(photoTagName);
        return ServerResponse.success(ResponseCodeEnum.INSERT_SUCCESS);
    }

    @GetMapping
    public ServerResponse selectAll(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        PageInfo pageInfo = photoTagService.selectAll(pageNum, pageSize);
        return ServerResponse.success(pageInfo);
    }

    @DeleteMapping("/{id}")
    public ServerResponse delete(@PathVariable(value = "id") Integer id) {
        photoTagService.delete(id);
        return ServerResponse.success(ResponseCodeEnum.DELETE_SUCCESS);
    }

    @PatchMapping("/name")
    public ServerResponse updateName(@RequestParam(value = "id") Integer id,
                                     @RequestParam(value = "name") String name) {
        photoTagService.updateName(id, name);
        return ServerResponse.success(ResponseCodeEnum.UPDATE_SUCCESS);
    }

    @PatchMapping("/status")
    public ServerResponse updateStatus(@RequestParam(value = "id") Integer id) {
        photoTagService.updateStatus(id);
        return ServerResponse.success(ResponseCodeEnum.UPDATE_SUCCESS);
    }
}
