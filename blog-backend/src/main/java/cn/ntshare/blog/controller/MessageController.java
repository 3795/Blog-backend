package cn.ntshare.blog.controller;

import cn.ntshare.blog.enums.ResponseCodeEnum;
import cn.ntshare.blog.pojo.Message;
import cn.ntshare.blog.service.MessageService;
import cn.ntshare.blog.vo.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping
    public ServerResponse query(@RequestParam(value = "status", required = false) Integer status) {
        List<Message> list = messageService.queryByStatus(status);
        return ServerResponse.success(list);
    }

    @GetMapping("/count")
    public ServerResponse countByStatus(@RequestParam(value = "status", required = false) Integer status) {
        Integer count = messageService.countByStatus(status);
        return ServerResponse.success(count);
    }

    @PatchMapping("/{id}")
    public ServerResponse updateStatus(@PathVariable("id") Integer id) {
        messageService.updateStatus(id);
        return ServerResponse.success(ResponseCodeEnum.UPDATE_SUCCESS);
    }

    @DeleteMapping("/{id}")
    public ServerResponse delete(@PathVariable("id") Integer id) {
        messageService.delete(id);
        return ServerResponse.success(ResponseCodeEnum.DELETE_SUCCESS);
    }

    @PostMapping("/empty")
    public ServerResponse empty() {
        messageService.empty();
        return ServerResponse.success(ResponseCodeEnum.DELETE_SUCCESS);
    }
}
