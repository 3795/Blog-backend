package com.seven.Blog.convert;

import com.seven.Blog.dto.CategoryDTO;
import com.seven.Blog.pojo.Category;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created By Seven.wk
 * Description: 将category转化为categoryDto
 * Created At 2018/10/12
 */
public class CategoryToCategoryDTO {

    public static CategoryDTO convert(Category category) {
        CategoryDTO categoryDto = new CategoryDTO();
        BeanUtils.copyProperties(category, categoryDto);
        return categoryDto;
    }

    public static List<CategoryDTO> convert(List<Category> categories) {
        return categories.stream()
                .map(CategoryToCategoryDTO::convert)
                .collect(Collectors.toList());
    }

}
