package cn.ntshare.blog.dao;

import cn.ntshare.blog.dto.ArticleDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By Q.Hao
 * Description: 文章Dao
 * Created At 2019/5/7
 */
@Repository
@Mapper
public interface ArticleMapper {

    List<ArticleDTO> selectAll();

    ArticleDTO selectById(Integer id);
}
