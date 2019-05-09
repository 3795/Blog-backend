package cn.ntshare.blog.service;

import cn.ntshare.blog.pojo.ImgRecord;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 图片上传记录
 * Created At 2019/01/07
 */
public interface ImgRecordService {

    /**
     * 添加图片记录
     * @param img
     * @return
     */
    Boolean insert(String img);

    /**
     * 更新轮播图图片记录
     * @param carouselImgId
     * @param img
     * @return
     */
    Boolean updateCarouselImgIdByImg(Integer carouselImgId, String img);

    /**
     * 删除轮播图图片记录
     * @param carouselImgId
     * @return
     */
    Boolean deleteCarouselImgId(Integer carouselImgId);

    /**
     * 更新用户头像记录
     * @param userId
     * @param avatar
     * @return
     */
    Boolean updateImgByUserId(Integer userId, String avatar);

    /**
     * 更新文章图片记录
     * @param articleId
     * @param img
     * @return
     */
    Boolean updateArticleIdByImg(Integer articleId, String img);

    /**
     * 更新文章图片记录
     * @param articleId
     * @param img
     * @return
     */
    Boolean updateImgByArticleId(Integer articleId, String img);

    /**
     * 删除文章图片记录
     * @param articleId
     * @return
     */
    Boolean deleteArticleId(Integer articleId);

    /**
     * 查询废弃的图片
     * @return
     */
    List<ImgRecord> queryDiscardImg();

    /**
     * 删除图片记录
     * @param ids
     * @return
     */
    Integer deleteRecord(List<Integer> ids);
}
