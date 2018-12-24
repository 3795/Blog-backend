package com.seven.Blog.dao;

import com.seven.Blog.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: TagsMapper
 * Created At 2018/12/24
 */
@Mapper
@Repository
public interface TagMapper {

    List<Tag> queryTags(@Param("status") Integer status);

    Tag queryTagById(int id);

    int insertTag(Tag tag);

    int updateTag(Tag tag);

    int updateStatus(int id);

    int deleteTag(int id);

}
