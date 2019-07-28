package cn.ntshare.Blog.service;

import cn.ntshare.Blog.dto.PhotoTagDTO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface PhotoTagService {

    void insert(String photoTagName);

    void updateName(Integer photoTagId, String photoTagName);

    void delete(Integer photoTagId);

    void updateStatus(Integer photoTagId);

    List<PhotoTagDTO> selectEnableTag();

    PageInfo selectAll(int pageNum, int pageSize);

    String selectNameById(Integer photoTagId);

    void increaseQuantity(Integer photoTagId);
}
