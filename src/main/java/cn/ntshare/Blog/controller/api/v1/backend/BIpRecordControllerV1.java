package cn.ntshare.Blog.controller.api.v1.backend;

import cn.ntshare.Blog.enums.ResponseCodeEnum;
import cn.ntshare.Blog.service.IpRecordService;
import cn.ntshare.Blog.vo.ServerResponse;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created By Seven.wk
 * Description: IP记录控制器
 * Created At 2019/01/11
 */
@RestController
@RequestMapping("/backend/ipRecord")
@Api(tags = "IP记录接口")
public class BIpRecordControllerV1 {

    @Autowired
    private IpRecordService ipRecordService;

    @GetMapping
    @ApiOperation("分页查询IP记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", defaultValue = "1", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "条数", defaultValue = "10", paramType = "query"),
    })
    public ServerResponse query(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        PageInfo pageInfo = ipRecordService.query(pageNum, pageSize);
        return ServerResponse.success(pageInfo);
    }

    @PatchMapping("/{id}")
    @ApiOperation("更新该IP记录的状态")
    @ApiImplicitParam(name = "id", value = "ID", required = true, paramType = "path")
    public ServerResponse updateStatus(@PathVariable("id") Integer id) {
        ipRecordService.updateStatus(id);
        return ServerResponse.success(ResponseCodeEnum.UPDATE_SUCCESS);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除IP记录")
    @ApiImplicitParam(name = "id", value = "ID", required = true, paramType = "path")
    public ServerResponse delete(@PathVariable("id") Integer id) {
        ipRecordService.delete(id);
        return ServerResponse.success(ResponseCodeEnum.DELETE_SUCCESS);
    }
}
