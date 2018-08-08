package com.seven.Blog.controller.api.manage;

import com.seven.Blog.form.CategoryForm;
import com.seven.Blog.response.ServerResponse;
import com.seven.Blog.service.CategoryService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created By Seven.wk
 * Description: 分类管理的api
 * Created At 2018/08/07
 */
@RestController
@RequestMapping("/api/manage/category")
public class ICategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public ServerResponse add(@Param("name") String name,
                              @Param("parentId") Integer parentId,
                              @Param("status") Integer status) {
        return categoryService.addCategory(name, parentId, status);
    }

    @PostMapping("/update")
    public ServerResponse update(@Valid CategoryForm categoryForm,
                                 BindingResult result) {
        if(result.hasErrors()) {
            return ServerResponse.error(result.getFieldError().getDefaultMessage());
        }
        Integer id = Integer.parseInt(categoryForm.getId());
        Integer parentId = Integer.parseInt(categoryForm.getParentId());
        Integer status = Integer.parseInt(categoryForm.getStatus());
        return categoryService.updateCategory(id, categoryForm.getName(), parentId, status);
    }

    @DeleteMapping("/{id}")
    public ServerResponse delete(@PathVariable("id") Integer id) {
        return categoryService.deleteCategory(id);
    }

    @PatchMapping("/{id}")
    public ServerResponse changeCategoryStatus(@PathVariable("id") Integer id) {
        return categoryService.changeCategoryStatus(id);
    }
}
