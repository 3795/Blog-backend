package cn.ntshare.Blog.controller.api.v1.frontend;

import cn.ntshare.Blog.dto.PhotoTagDTO;
import cn.ntshare.Blog.service.PhotoTagService;
import cn.ntshare.Blog.vo.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/photoTag")
public class FPhotoTagControllerV1 {

    @Autowired
    private PhotoTagService photoTagService;

    @GetMapping("/enable")
    public ServerResponse enableTag() {
        List<PhotoTagDTO> photoTagList = photoTagService.selectEnableTag();
        return ServerResponse.success(photoTagList);
    }
}
