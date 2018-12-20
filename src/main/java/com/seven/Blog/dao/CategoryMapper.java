package com.seven.Blog.dao;

import com.seven.Blog.dto.ChildrenCateDTO;
import com.seven.Blog.dto.ParentCateDTO;
import com.seven.Blog.dto.CategoryDTO;
import com.seven.Blog.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 文章分类Dao层
 * Created At 2018/08/07
 */
@Repository
@Mapper
public interface CategoryMapper {

    List<CategoryDTO> selectAll();

    CategoryDTO selectById(Integer id);

    List<CategoryDTO> selectByStatus(Integer status);

    List<CategoryDTO> selectFirstLevel();

    Integer selectStatusById(Integer id);

    int insert(Category category);

    int update(Category category);

    int updateStatus(@Param("id") Integer id,
                     @Param("status") Integer status);

    int delete(Integer id);

    List<ParentCateDTO> selectParent();

    List<ChildrenCateDTO> selectChildren(Integer parentId);

    List<Integer> selectChildrenId(Integer id);

    List<CategoryDTO> selectChildrenInfo(Integer parentId);

    int count();

}
