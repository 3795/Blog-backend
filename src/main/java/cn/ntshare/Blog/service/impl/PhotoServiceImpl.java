package cn.ntshare.Blog.service.impl;

import cn.ntshare.Blog.dao.PhotoMapper;
import cn.ntshare.Blog.dao.PhotoTagMapper;
import cn.ntshare.Blog.dto.PhotoDTO;
import cn.ntshare.Blog.enums.CommonStatusEnum;
import cn.ntshare.Blog.enums.ResponseCodeEnum;
import cn.ntshare.Blog.exception.SystemException;
import cn.ntshare.Blog.pojo.Photo;
import cn.ntshare.Blog.service.ImgRecordService;
import cn.ntshare.Blog.service.PhotoService;
import cn.ntshare.Blog.service.PhotoTagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created By Q.Hao
 * Description:
 * Created At 2019/7/28
 */
@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private ImgRecordService imgRecordService;

    @Autowired
    private PhotoMapper photoMapper;

    @Autowired
    private PhotoTagService photoTagService;

    @Override
    @Transactional
    public void insert(Photo photo) {
        int result = photoMapper.insert(photo);
        if (result != 1) {
            throw new SystemException(ResponseCodeEnum.INSERT_FAILED);
        }

        imgRecordService.updatePhotoIdByImg(photo.getId(), photo.getImg());

        photoTagService.increaseQuantity(photo.getPhotoTagId());
    }

    @Override
    public void update(Photo photo) {

    }

    @Override
    public PageInfo selectAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<PhotoDTO> photoDTOList = photoMapper.selectAll();
        return new PageInfo<>(photoDTOList);
    }

    @Override
    public void updateStatus(Integer id) {
        int result = photoMapper.updateStatus(id);
        if (result != 1) {
            throw new SystemException(ResponseCodeEnum.UPDATE_FAILED);
        }
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Photo photo = photoMapper.queryById(id);
        if (photo == null) {
            throw new SystemException(ResponseCodeEnum.PAGE_NOT_FOUND);
        }
        int result = photoMapper.delete(photo.getId());
        if (result != 1) {
            throw new SystemException(ResponseCodeEnum.DELETE_FAILED);
        }

        imgRecordService.deleteByPhotoId(id);

        photoTagService.decreaseQuantity(photo.getPhotoTagId());
    }

    @Override
    public PageInfo selectAllByStatus(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<PhotoDTO> photoDTOList = photoMapper.selectByStatus(CommonStatusEnum.ON.getCode());
        for (PhotoDTO item : photoDTOList) {
            String name = photoTagService.selectNameById(item.getPhotoTagId());
            if (name == null) {
                name = "暂无分类";
            }
            item.setPhotoTagName(name);
        }
        return new PageInfo<>(photoDTOList);
    }


}
