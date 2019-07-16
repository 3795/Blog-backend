package cn.ntshare.Blog.controller.api.v1.frontend;

import cn.ntshare.Blog.constant.SystemConstant;
import cn.ntshare.Blog.enums.CommonStatusEnum;
import cn.ntshare.Blog.service.ArticleService;
import cn.ntshare.Blog.service.CategoryService;
import cn.ntshare.Blog.util.RedisUtil;
import cn.ntshare.Blog.vo.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 网站数据查询接口
 */
@RestController
@RequestMapping("/data")
public class FDataControllerV1 {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    /**
     * 统计文章数量和分类数量
     * @return
     */
    @GetMapping("/count")
    public ServerResponse count() {
        int articleCount = articleService.countByStatusAndType(CommonStatusEnum.ON.getCode(), null);
        int categoryCount = categoryService.count();
        Map<String, Integer> data = new HashMap<>();
        data.put("articleCount", articleCount);
        data.put("categoryCount", categoryCount);
        return ServerResponse.success(data);
    }

    /**
     * 统计网站运行时间
     * @return
     */
    @GetMapping("/runtime")
    public ServerResponse runtime() {
        String days = RedisUtil.get(SystemConstant.RUNTIME_DAYS);
        String seconds = RedisUtil.get(SystemConstant.RUNTIME_SECONDS);
        Map<String, Integer> data = new HashMap<>();
        data.put("days", Integer.parseInt(days));
        data.put("seconds", Integer.parseInt(seconds));
        return ServerResponse.success(data);
    }
}
