package com.seven.Blog.dao;

import com.seven.Blog.bo.ChildrenCateBO;
import com.seven.Blog.bo.ParentCateBO;
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

    Category selectedByPrimaryKey(Integer key);

    List<Category> getAllCategory(@Param("offset") Integer offset,
                                  @Param("size") Integer size);

    int addCategory(@Param("name") String name,
                    @Param("parentId") Integer parentId,
                    @Param("status") Integer status);

    int updateCategory(Category category);

    int deleteCategoryByPrimaryKey(Integer key);

    String selectedCategoryNameByPrimaryKey(Integer key);

    List<Category> getCategoriesByStatus(Integer status);

    List<Category> getChildCategory(Integer parentId);

    /**
     * 获得所有分类的数量
     * @return
     */
    int getCategoryCount();

    /*--------------------------二期新增--------------------------------*/

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

    List<ParentCateBO> selectParent();

    List<ChildrenCateBO> selectChildren(Integer parentId);

}
