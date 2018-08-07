package com.seven.Blog.dao;

import com.seven.Blog.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 文章分类Dao层
 * Created At 2018/08/07
 */
@Mapper
public interface CategoryMapper {

    List<Category> getAllCategory();

    int addCategory(@Param("name") String name,
                    @Param("parentId") Integer parentId,
                    @Param("status") Integer status);

    int updateCategoryByPrimaryKey(Category category);

    int deleteCategoryByPrimaryKey(Integer key);
}
