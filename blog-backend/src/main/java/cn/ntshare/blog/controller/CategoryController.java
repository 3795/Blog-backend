package cn.ntshare.blog.controller;

import cn.ntshare.blog.dto.CategoryDTO;
import cn.ntshare.blog.dto.ParentCateDTO;
import cn.ntshare.blog.enums.ResponseCodeEnum;
import cn.ntshare.blog.form.CategoryForm;
import cn.ntshare.blog.pojo.Category;
import cn.ntshare.blog.service.CategoryService;
import cn.ntshare.blog.vo.ServerResponse;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 获取所有的分类信息
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping
    public ServerResponse getCategories(@RequestParam(value = "status", defaultValue = "-1") Integer status,
                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                        @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        PageInfo pageInfo;
        if (status == -1) {
            pageInfo = categoryService.selectAll(pageNum, pageSize);
        } else {
            pageInfo = categoryService.selectByStatus(status, pageNum, pageSize);
        }
        return ServerResponse.success(pageInfo);
    }

    /**
     * 根据ID获取分类信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ServerResponse getCategoryById(@PathVariable("id") Integer id) {
        CategoryDTO category = categoryService.selectById(id);
        return ServerResponse.success(category);
    }

    /**
     * 获取一级分类信息
     * @return
     */
    @GetMapping("/firstLevel")
    public ServerResponse getFirstLevel() {
        List<CategoryDTO> categoryDTOS = categoryService.selectFirstLevel();
        return ServerResponse.success(categoryDTOS);
    }

    /**
     * 新建分类
     * @param categoryForm
     * @param result
     * @return
     */
    @PostMapping
    public ServerResponse createCategory(@Valid CategoryForm categoryForm,
                                         BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.error(result.getFieldError().getDefaultMessage());
        }

        Category category = new Category(categoryForm.getName(),
                Integer.parseInt(categoryForm.getParentId()),
                Integer.parseInt(categoryForm.getStatus()));

        categoryService.insert(category);
        return ServerResponse.success(ResponseCodeEnum.INSERT_SUCCESS);
    }

    /**
     * 更新分类
     * @param id
     * @param categoryForm
     * @param result
     * @return
     */
    @PutMapping
    public ServerResponse updateCategory(@RequestParam(value = "id") Integer id,
                                         @Valid CategoryForm categoryForm,
                                         BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.error(result.getFieldError().getDefaultMessage());
        }

        Category category = new Category(id, categoryForm.getName(),
                Integer.parseInt(categoryForm.getParentId()),
                Integer.parseInt(categoryForm.getStatus()));

        categoryService.update(category);
        return ServerResponse.success(ResponseCodeEnum.UPDATE_SUCCESS);
    }

    /**
     * 更新分类局部信息
     * @param id
     * @return
     */
    @PatchMapping("/{id}")
    public ServerResponse changeCategoryStatus(@PathVariable("id") Integer id) {
        categoryService.changeStatus(id);
        return ServerResponse.success(ResponseCodeEnum.UPDATE_SUCCESS);
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ServerResponse deleteCategory(@PathVariable(value = "id") Integer id) {
        categoryService.delete(id);
        return ServerResponse.success(ResponseCodeEnum.DELETE_SUCCESS);
    }

    /**
     * 级联查找分类
     * @return
     */
    @GetMapping("/cascade")
    public ServerResponse selectCascadeCategory() {
        List<ParentCateDTO> parentCateDTOList = categoryService.selectCascadeCate();
        return ServerResponse.success(parentCateDTOList);
    }
}
