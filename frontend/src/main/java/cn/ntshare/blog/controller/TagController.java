package cn.ntshare.blog.controller;

import cn.ntshare.blog.dto.TagDTO;
import cn.ntshare.blog.service.TagService;
import cn.ntshare.blog.vo.ServerResponse;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping
    public ServerResponse queryTags() {
        List<TagDTO> list = tagService.queryTags();
        return ServerResponse.success(list);
    }

    @GetMapping("/{id}")
    public ServerResponse queryTagById(@PathVariable("id") Integer id) {
        TagDTO tag = tagService.queryTagById(id);
        return ServerResponse.success(tag);
    }

    @GetMapping("/article")
    public ServerResponse queryArticlesById(@RequestParam("id") Integer id,
                                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        PageInfo pageInfo = tagService.queryArticlesById(id, pageNum, pageSize);
        return ServerResponse.success(pageInfo);
    }
}
