package cn.ntshare.Blog.service;

import cn.ntshare.Blog.pojo.Photo;
import com.github.pagehelper.PageInfo;

public interface PhotoService {

    void insert(Photo photo);

    void update(Photo photo);

    PageInfo selectAll(Integer pageNum, Integer pageSize);

    void updateStatus(Integer id);

    void delete(Integer id);

    PageInfo selectAllByStatus(Integer pageNum, Integer pageSize);
}
