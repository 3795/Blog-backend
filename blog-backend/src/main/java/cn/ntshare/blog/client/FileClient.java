package cn.ntshare.blog.client;

import cn.ntshare.blog.vo.ServerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(value = "blog-file")
public interface FileClient {

    @PostMapping(value = "/img/delete")
    ServerResponse deleteImg(@RequestParam("imgName") String imgName);

    @PostMapping(value = "/imgs/delete")
    ServerResponse deleteImgs(@RequestBody List<String> imgList);

}
