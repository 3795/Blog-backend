package cn.ntshare.Blog.controller.api.v1.frontend;

import cn.ntshare.Blog.dto.TagDTO;
import cn.ntshare.Blog.service.TagService;
import cn.ntshare.Blog.vo.ServerResponse;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 标签功能控制器
 * Created At 2019/01/01
 */
@RestController
@RequestMapping("/tag")
@Api(tags = "前台文章标签接口")
public class FTagControllerV1 {

    @Autowired
    private TagService tagService;

    @GetMapping
    @ApiOperation(value = "查询所有启用的标签")
    public ServerResponse queryTags() {
        List<TagDTO> list = tagService.queryTags();
        return ServerResponse.success(list);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id值查找标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID值", required = true, paramType = "path")
    })
    public ServerResponse queryTagById(@PathVariable("id") Integer id) {
        TagDTO tag = tagService.queryTagById(id);
        return ServerResponse.success(tag);
    }

    @GetMapping("/article")
    @ApiOperation(value = "根据标签查找文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID值", required = true, paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页码", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示数量", paramType = "query")
    })
    public ServerResponse queryArticlesById(@RequestParam("id") Integer id,
                                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        PageInfo pageInfo = tagService.queryArticlesById(id, pageNum, pageSize);
        return ServerResponse.success(pageInfo);
    }

}
