package cn.ntshare.blog.service;

import cn.ntshare.blog.dto.TagDTO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: Tag Service
 * Created At 2018/12/24
 */
public interface TagService {

    List<TagDTO> queryTags();

    TagDTO queryTagById(Integer id);

    PageInfo queryArticlesById(Integer id, int pageNum, int pageSize);

    int count();
}
