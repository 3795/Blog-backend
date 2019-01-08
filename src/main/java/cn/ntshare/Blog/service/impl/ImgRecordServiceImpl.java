package cn.ntshare.Blog.service.impl;

import cn.ntshare.Blog.exception.SystemException;
import cn.ntshare.Blog.dao.ImgRecordMapper;
import cn.ntshare.Blog.enums.ResponseCodeEnum;
import cn.ntshare.Blog.pojo.ImgRecord;
import cn.ntshare.Blog.service.ImgRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 实现层
 * Created At 2019/01/07
 */
@Service
@Slf4j
public class ImgRecordServiceImpl implements ImgRecordService {

    @Autowired
    private ImgRecordMapper imgRecordMapper;

    @Override
    public Boolean insert(String img) {
        int result = imgRecordMapper.insert(img);
        if (result != 1) {
            log.warn("insert img_record error!");
            throw new SystemException(ResponseCodeEnum.INSERT_FAILED);
        }
        return true;
    }

    @Override
    public Boolean updateCarouselImgIdByImg(Integer carouselImgId, String img) {
        ImgRecord imgRecord = new ImgRecord(img);
        imgRecord.setCarouselImgId(carouselImgId);
        int result = imgRecordMapper.updateIdByImg(imgRecord);
        if (result != 1) {
            log.warn("update img_record error!");
            throw new SystemException(ResponseCodeEnum.INSERT_FAILED);
        }
        return true;
    }

    @Override
    public Boolean updateImgByCarouselImgId(Integer carouselImgId, String img) {
        ImgRecord imgRecord = new ImgRecord(img);
        imgRecord.setCarouselImgId(carouselImgId);
        if (checkImgChangeById(imgRecord)) {
            deleteCarouselImgId(carouselImgId);
            updateCarouselImgIdByImg(carouselImgId, img);
            return true;
        }
        return true;
    }

    @Override
    public Boolean deleteCarouselImgId(Integer id) {
        ImgRecord imgRecord = new ImgRecord();
        imgRecord.setCarouselImgId(id);
        int result = imgRecordMapper.deleteId(imgRecord);
        if (result != 1) {
            log.warn("update img_record error!");
            throw new SystemException(ResponseCodeEnum.DELETE_FAILED);
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean updateImgByUserId(Integer userId, String avatar) {
        ImgRecord imgRecord = new ImgRecord(avatar);
        imgRecord.setUserId(userId);
        int resultA = imgRecordMapper.deleteId(imgRecord);
        int resultB = imgRecordMapper.updateIdByImg(imgRecord);
        if (resultA != 1 || resultB != 1) {
            log.warn("update img_record error!");
            throw new SystemException(ResponseCodeEnum.UPDATE_FAILED);
        }
        return true;
    }

    @Override
    public Boolean updateArticleIdByImg(Integer articleId, String img) {
        ImgRecord imgRecord = new ImgRecord(img);
        imgRecord.setArticleId(articleId);
        int result = imgRecordMapper.updateIdByImg(imgRecord);
        if (result != 1) {
            log.warn("update img_record error!");
            throw new SystemException(ResponseCodeEnum.UPDATE_FAILED);
        }
        return true;
    }

    @Override
    public Boolean updateImgByArticleId(Integer articleId, String img) {
        ImgRecord imgRecord = new ImgRecord(img);
        imgRecord.setArticleId(articleId);
        // 检查是否更新图片
        if (checkImgChangeById(imgRecord)) {
            deleteArticleId(articleId);
            updateArticleIdByImg(articleId, img);
            return true;
        }
        // 没有更新图片，直接返回true
        return true;
    }

    @Override
    public Boolean deleteArticleId(Integer articleId) {
        ImgRecord imgRecord = new ImgRecord();
        imgRecord.setArticleId(articleId);
        int result = imgRecordMapper.deleteId(imgRecord);
        if (result != 1) {
            log.warn("update img_record error!");
            throw new SystemException(ResponseCodeEnum.DELETE_FAILED);
        }
        return true;
    }

    @Override
    public List<ImgRecord> queryDiscardImg() {
        return imgRecordMapper.queryDiscardImg();
    }

    @Override
    public Integer deleteRecord(List<Integer> ids) {
        if (ids.size() == 0) {
            return 0;
        }
        int result = imgRecordMapper.deleteRecord(ids);
        log.info("共删除 {} 条记录", result);
        return result;
    }

    /**
     * 检查是否需要更改图片记录
     * @param imgRecord
     * @return
     */
    private Boolean checkImgChangeById(ImgRecord imgRecord) {
        int result = imgRecordMapper.checkImgChangeById(imgRecord);
        return result != 1;
    }
}
