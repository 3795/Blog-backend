package cn.ntshare.blog.controller;

import cn.ntshare.blog.dto.MonitorDTO;
import cn.ntshare.blog.service.MonitorService;
import cn.ntshare.blog.vo.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monitor")
public class MonitorController {

    @Autowired
    private MonitorService monitorService;

    @GetMapping("/cardData")
    public ServerResponse queryCardData() {
        MonitorDTO monitorDTO = monitorService.queryData();
        return ServerResponse.success(monitorDTO);
    }
}
