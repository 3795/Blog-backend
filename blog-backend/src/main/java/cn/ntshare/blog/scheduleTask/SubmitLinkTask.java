package cn.ntshare.blog.scheduleTask;

import cn.ntshare.blog.constant.SystemConstant;
import cn.ntshare.blog.dto.BaiduLinkSubmissionDTO;
import cn.ntshare.blog.service.MessageService;
import cn.ntshare.blog.util.HttpUtil;
import cn.ntshare.blog.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 定时向百度站点提交最新的文章链接
 */
@Component
@Slf4j
public class SubmitLinkTask {

    @Autowired
    private MessageService messageService;

    /**
     * 每天23点30分执行
     */
    @Scheduled(cron = "0 30 23 * * *")
    public void task() {
        List<String> urls = RedisUtil.getList(SystemConstant.INDEX_LINKS);
        if (urls.size() == 0) {
            log.info("无链接需要向百度搜索提交");
        } else {
            BaiduLinkSubmissionDTO result = HttpUtil.baiduLinkSubmission(urls);
            String content = "链接推送成功，成功推送 " + result.getSuccess() + " 条链接";
            messageService.insert("百度搜索推送链接", content);
            log.info("链接推送成功，成功推送 {} 条链接", result.getSuccess());
        }
    }
}
