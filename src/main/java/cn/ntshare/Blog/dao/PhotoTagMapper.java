package cn.ntshare.Blog.dao;

import cn.ntshare.Blog.dto.PhotoTagDTO;
import cn.ntshare.Blog.pojo.PhotoTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PhotoTagMapper {

    int insert(PhotoTag photoTag);

    int update(PhotoTag photoTag);

    int updateStatus(@Param("id") Integer id);

    int delete(@Param("id") Integer id);

    List<PhotoTagDTO> selectByStatus(@Param("status") Integer status);

    List<PhotoTagDTO> selectAll();

    int increaseQuantity(Integer photoTagId);

    int decreaseQuantity(Integer photoTagId);

}
