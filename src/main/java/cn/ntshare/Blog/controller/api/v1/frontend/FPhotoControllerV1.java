package cn.ntshare.Blog.controller.api.v1.frontend;

import cn.ntshare.Blog.service.PhotoService;
import cn.ntshare.Blog.vo.ServerResponse;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By Q.Hao
 * Description:
 * Created At 2019/7/28
 */
@RestController
@RequestMapping("/photo")
public class FPhotoControllerV1 {

    @Autowired
    private PhotoService photoService;

    @GetMapping
    public ServerResponse select(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                 @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        PageInfo pageInfo = photoService.selectAllByStatus(pageNum, pageSize);
        return ServerResponse.success(pageInfo);
    }
}
