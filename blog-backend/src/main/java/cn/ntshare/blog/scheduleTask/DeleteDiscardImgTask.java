package cn.ntshare.blog.scheduleTask;

import cn.ntshare.blog.client.FileClient;
import cn.ntshare.blog.pojo.ImgRecord;
import cn.ntshare.blog.service.ImgRecordService;
import cn.ntshare.blog.service.MessageService;
import cn.ntshare.blog.util.FileUtil;
import cn.ntshare.blog.vo.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 定时删除废弃图片
 */
@Component
@Slf4j
public class DeleteDiscardImgTask {
    @Autowired
    private ImgRecordService imgRecordService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private FileClient fileClient;

    /**
     * 每天凌晨1点执行
     */
    @Scheduled(cron = "0 0 1 * * *")
    public void task() {
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

        for (String img : imgNames) {
            ServerResponse response = fileClient.deleteImg(img);
            if (response.getCode()%2 != 1) {
                log.error(response.getMsg());
            } else {
                log.info(response.getMsg());
            }
        }

        // 删除数据库中的记录
        List<Integer> ids = list.stream()
                .map(ImgRecord::getId)
                .collect(Collectors.toList());

        Integer count = imgRecordService.deleteRecord(ids);
        messageService.insert("定时任务", "删除冗余图片成功，共删除 " + count + "张图片！");
    }
}
