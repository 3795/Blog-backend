package cn.ntshare.blog.service;

import cn.ntshare.blog.dto.CategoryDTO;
import cn.ntshare.blog.dto.CategoryInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 文章分类接口
 * Created At 2018/08/07
 */
public interface CategoryService {

    PageInfo selectAll(int pageNum, int pageSize);

    CategoryDTO selectById(Integer id);

    PageInfo selectByStatus(int status, int pageNum, int pageSize);

    List<CategoryDTO> selectFirstLevel();

    List<Integer> selectChildrenId(Integer id);

    CategoryInfo selectParentAndChildren(Integer id);

    int count();
}
