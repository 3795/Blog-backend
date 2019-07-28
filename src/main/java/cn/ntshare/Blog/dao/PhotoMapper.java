package cn.ntshare.Blog.dao;

import cn.ntshare.Blog.dto.PhotoDTO;
import cn.ntshare.Blog.pojo.Photo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PhotoMapper {

    int insert(Photo photo);

    int update(Photo photo);

    int delete(Integer photoId);

    int updateStatus(Integer photoId);

    List<PhotoDTO> selectByStatus(@Param("status") Integer status);

    List<PhotoDTO> selectAll();

    Photo queryById(Integer id);
}
