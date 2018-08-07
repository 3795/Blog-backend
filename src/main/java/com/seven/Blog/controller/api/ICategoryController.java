package com.seven.Blog.controller.api;

import com.seven.Blog.form.CategoryForm;
import com.seven.Blog.response.ServerResponse;
import com.seven.Blog.service.CategoryService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created By Seven.wk
 * Description: 分类管理的api
 * Created At 2018/08/07
 */
@RestController
@RequestMapping("/api/category")
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
        //todo 将字符串转化为Integer类型，并完善该api
        return ServerResponse.success("数据没问题");
    }
}
