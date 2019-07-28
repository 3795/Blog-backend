package cn.ntshare.Blog.service.impl;

import cn.ntshare.Blog.dao.PhotoTagMapper;
import cn.ntshare.Blog.dto.PhotoTagDTO;
import cn.ntshare.Blog.enums.CommonStatusEnum;
import cn.ntshare.Blog.enums.ResponseCodeEnum;
import cn.ntshare.Blog.exception.SystemException;
import cn.ntshare.Blog.pojo.PhotoTag;
import cn.ntshare.Blog.service.PhotoTagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PhotoTagServiceImpl implements PhotoTagService {

    @Autowired
    private PhotoTagMapper photoTagMapper;

    @Override
    public void insert(String photoTagName) {
        PhotoTag photoTag = new PhotoTag(photoTagName, CommonStatusEnum.ON.getCode(), 0);
        int result = photoTagMapper.insert(photoTag);
        if (result != 1) {
            log.error("insert into photo_tag error");
            throw new SystemException(ResponseCodeEnum.INSERT_FAILED);
        }
    }

    @Override
    public void updateName(Integer photoTagId, String photoTagName) {
        PhotoTag photoTag = new PhotoTag(photoTagId, photoTagName);
        int result = photoTagMapper.update(photoTag);
        if (result != 1) {
            log.error("update photo_tag error");
            throw new SystemException(ResponseCodeEnum.UPDATE_FAILED);
        }
    }

    @Override
    public void delete(Integer photoTagId) {
        int result = photoTagMapper.delete(photoTagId);
        if (result != 1) {
            log.error("delete photo_tag error");
            throw new SystemException(ResponseCodeEnum.UPDATE_FAILED);
        }
    }

    @Override
    public void updateStatus(Integer photoTagId) {
        int result = photoTagMapper.updateStatus(photoTagId);
        if (result != 1) {
            log.error("update photo_tag error");
            throw new SystemException(ResponseCodeEnum.UPDATE_FAILED);
        }
    }

    @Override
    public List<PhotoTagDTO> selectEnableTag() {
        return photoTagMapper.selectByStatus(CommonStatusEnum.ON.getCode());
    }

    @Override
    public PageInfo selectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<PhotoTagDTO> photoTagDTOList = photoTagMapper.selectAll();
        return new PageInfo<>(photoTagDTOList);
    }

    @Override
    public String selectNameById(Integer photoTagId) {
        return photoTagMapper.selectNameById(photoTagId);
    }
}
