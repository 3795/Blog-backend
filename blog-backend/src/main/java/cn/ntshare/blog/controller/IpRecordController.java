package cn.ntshare.blog.controller;

import cn.ntshare.blog.enums.ResponseCodeEnum;
import cn.ntshare.blog.service.IpRecordService;
import cn.ntshare.blog.vo.ServerResponse;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ipRecord")
public class IpRecordController {

    @Autowired
    private IpRecordService ipRecordService;

    /**
     * 分页查询IP记录
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping
    public ServerResponse query(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        PageInfo pageInfo = ipRecordService.query(pageNum, pageSize);
        return ServerResponse.success(pageInfo);
    }

    /**
     * 更新IP记录状态
     * @param id
     * @return
     */
    @PatchMapping("/{id}")
    public ServerResponse updateStatus(@PathVariable("id") Integer id) {
        ipRecordService.updateStatus(id);
        return ServerResponse.success(ResponseCodeEnum.UPDATE_SUCCESS);
    }

    /**
     * 删除IP记录
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ServerResponse delete(@PathVariable("id") Integer id) {
        ipRecordService.delete(id);
        return ServerResponse.success(ResponseCodeEnum.DELETE_SUCCESS);
    }
}
