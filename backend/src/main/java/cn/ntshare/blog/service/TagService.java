package cn.ntshare.blog.service;

import cn.ntshare.blog.dto.TagDTO;
import cn.ntshare.blog.pojo.Tag;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: Tag Service
 * Created At 2018/12/24
 */
public interface TagService {

    PageInfo queryTags(Integer status, int pageNum, int pageSize);

    List<TagDTO> queryTags();

    List<TagDTO> queryTagOptions();

    TagDTO queryTagById(Integer id);

    boolean insertTag(Tag tag);

    boolean updateTag(Tag tag);

    boolean updateStatus(Integer id);

    boolean deleteTag(Integer id);

    PageInfo queryArticlesById(Integer id, int pageNum, int pageSize);

    int count();
}
