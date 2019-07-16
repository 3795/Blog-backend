package cn.ntshare.Blog.scheduleTask;

import cn.ntshare.Blog.pojo.ImgRecord;
import cn.ntshare.Blog.pojo.Message;
import cn.ntshare.Blog.service.ImgRecordService;
import cn.ntshare.Blog.service.RabbitMqService;
import cn.ntshare.Blog.util.FileUtil;
import cn.ntshare.Blog.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static cn.ntshare.Blog.constant.SystemConstant.REDIS_LOCK_KEY;
import static cn.ntshare.Blog.constant.SystemConstant.REDIS_LOCK_TIME;

/**
 * Created By Seven.wk
 * Description: 定时删除废弃的图片
 * Created At 2019/01/08
 */
@Component
@Slf4j
public class DeleteDiscardImgTask {

    @Autowired
    private ImgRecordService imgRecordService;

    @Value("${server.port}")
    public String redisLockValue;

    @Autowired
    private RabbitMqService rabbitMqService;

    /**
     * 每天凌晨1点执行
     */
    @Scheduled(cron = "0 0 1 * * *")
    public void task() {
        if (!RedisUtil.getRedisLock(REDIS_LOCK_KEY, redisLockValue, REDIS_LOCK_TIME)) {
            return;
        }
        List<ImgRecord> list = imgRecordService.queryDiscardImg();
        if (list.size() == 0) {
            log.info("没有冗余图片需要删除");
            return;
        }

        // 删除图片
        List<String> imgNames = list.stream()
                .map(ImgRecord::getImg)
                .map(s -> s.substring(s.lastIndexOf("/") + 1))
                .collect(Collectors.toList());

        FileUtil.deleteImgs(imgNames);

        // 删除数据库中的记录
        List<Integer> ids = list.stream()
                .map(ImgRecord::getId)
                .collect(Collectors.toList());

        Integer count = imgRecordService.deleteRecord(ids);
        rabbitMqService.sendNotice(new Message("定时任务", "删除冗余图片成功，共删除 " + count + "张图片！"));
        RedisUtil.delRedisLock(REDIS_LOCK_KEY, redisLockValue);
    }
}
