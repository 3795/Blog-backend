package cn.ntshare.blog.dao;

import cn.ntshare.blog.dto.ArticleDTO;
import cn.ntshare.blog.dto.TagDTO;
import cn.ntshare.blog.pojo.Tag;
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

    List<TagDTO> queryTags(@Param("status") Integer status);

    List<TagDTO> queryTagOptions();

    TagDTO queryTagById(int id);

    int insertTag(Tag tag);

    int updateTag(Tag tag);

    int updateStatus(int id);

    int deleteTag(int id);

    List<TagDTO> queryEnableTags();

    List<ArticleDTO> queryArticlesById(Integer id);

    int count();
}
