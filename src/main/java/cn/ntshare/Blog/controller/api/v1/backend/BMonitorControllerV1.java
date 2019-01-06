package cn.ntshare.Blog.controller.api.v1.backend;

import cn.ntshare.Blog.dto.MonitorDTO;
import cn.ntshare.Blog.service.MonitorService;
import cn.ntshare.Blog.vo.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By Seven.wk
 * Description: 系统检测控制器
 * Created At 2019/01/06
 */
@RestController
@RequestMapping("/backend/monitor")
@Api(tags = "系统检测控制器")
public class BMonitorControllerV1 {

    @Autowired
    private MonitorService monitorService;

    @GetMapping
    @ApiOperation("查询基础数据")
    public ServerResponse queryData() {
        MonitorDTO monitorDTO = monitorService.queryData();
        return ServerResponse.success(monitorDTO);
    }
}
