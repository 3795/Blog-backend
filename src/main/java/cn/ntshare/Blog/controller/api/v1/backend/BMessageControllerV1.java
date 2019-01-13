package cn.ntshare.Blog.controller.api.v1.backend;

import cn.ntshare.Blog.enums.ResponseCodeEnum;
import cn.ntshare.Blog.pojo.Message;
import cn.ntshare.Blog.service.MessageService;
import cn.ntshare.Blog.vo.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 系统消息接口
 * Created At 2019/01/11
 */
@RestController
@RequestMapping("/backend/message")
@Api(tags = "系统消息接口")
public class BMessageControllerV1 {

    @Autowired
    private MessageService messageService;

    @GetMapping
    @ApiOperation("根据消息状态查询消息")
    @ApiImplicitParam(name = "status", value = "消息状态", paramType = "query")
    public ServerResponse query(@RequestParam(value = "status", required = false) Integer status) {
        List<Message> list = messageService.queryByStatus(status);
        return ServerResponse.success(list);
    }

    @GetMapping("/count")
    @ApiOperation("根据状态统计消息数量")
    @ApiImplicitParam(name = "status", value = "消息状态", paramType = "query")
    public ServerResponse countByStatus(@RequestParam(value = "status", required = false) Integer status) {
        Integer count = messageService.countByStatus(status);
        return ServerResponse.success(count);
    }

    @PatchMapping("/{id}")
    @ApiOperation("更新消息状态")
    @ApiImplicitParam(name = "id", value = "ID", required = true, paramType = "path")
    public ServerResponse updateStatus(@PathVariable("id") Integer id) {
        messageService.updateStatus(id);
        return ServerResponse.success(ResponseCodeEnum.UPDATE_SUCCESS);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除消息")
    @ApiImplicitParam(name = "id", value = "ID", required = true, paramType = "path")
    public ServerResponse delete(@PathVariable("id") Integer id) {
        messageService.delete(id);
        return ServerResponse.success(ResponseCodeEnum.DELETE_SUCCESS);
    }

    @PostMapping("/empty")
    @ApiOperation("删除已读消息")
    public ServerResponse empty() {
        messageService.empty();
        return ServerResponse.success(ResponseCodeEnum.DELETE_SUCCESS);
    }

}
