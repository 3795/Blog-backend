package com.seven.Blog.controller.api.v1.backend;

import com.github.pagehelper.PageInfo;
import com.seven.Blog.dto.NavigationDTO;
import com.seven.Blog.enums.ResponseCodeEnum;
import com.seven.Blog.form.NavigationForm;
import com.seven.Blog.pojo.Navigation;
import com.seven.Blog.service.NavigationService;
import com.seven.Blog.vo.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created By Seven.wk
 * Description: 导航信息管理
 * Created At 2018/11/24
 */
@RestController
@RequestMapping("/blog/v1/backend/navigation")
public class BNavigationControllerV1 {

    @Autowired
    private NavigationService navigationService;

    /**
     * 获取所有的导航信息
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping
    public ServerResponse getNavigations(@RequestParam(value = "status", defaultValue = "-1") Integer status,
                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                        @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        PageInfo pageInfo;
        if (status == -1) {
            pageInfo = navigationService.selectAll(pageNum, pageSize);
        } else {
            pageInfo = navigationService.selectByStatus(status, pageNum, pageSize);
        }
        return ServerResponse.success(pageInfo);
    }

    /**
     * 根据ID获取导航信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ServerResponse getNavigationById(@PathVariable("id") Integer id) {
        NavigationDTO navigationDTO = navigationService.selectById(id);
        return ServerResponse.success(navigationDTO);
    }

    /**
     * 新建导航信息
     * @param navigationForm
     * @param result
     * @return
     */
    @PostMapping
    public ServerResponse createNavigation(@Valid NavigationForm navigationForm,
                                         BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.error(result.getFieldError().getDefaultMessage());
        }

        Navigation navigation = new Navigation(navigationForm.getName(), Integer.parseInt(navigationForm.getPriority()),
                navigationForm.getLink(), Integer.parseInt(navigationForm.getStatus()));

        navigationService.insert(navigation);
        return ServerResponse.success(ResponseCodeEnum.INSERT_SUCCESS);
    }

    /**
     * 更新导航信息
     * @param id
     * @param navigationForm
     * @param result
     * @return
     */
    @PutMapping
    public ServerResponse updateNavigation(@RequestParam(value = "id") Integer id,
                                         @Valid NavigationForm navigationForm,
                                         BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.error(result.getFieldError().getDefaultMessage());
        }

        Navigation navigation = new Navigation(id, navigationForm.getName(),
                Integer.parseInt(navigationForm.getPriority()), navigationForm.getLink(),
                Integer.parseInt(navigationForm.getStatus()));

        navigationService.update(navigation);
        return ServerResponse.success(ResponseCodeEnum.UPDATE_SUCCESS);
    }

    /**
     * 更新导航的状态
     * @param id
     * @param status
     * @return
     */
    @PatchMapping
    public ServerResponse updateNavigationStatus(@RequestParam("id") Integer id,
                                                 @RequestParam("status") Integer status) {
        navigationService.updateStatus(id, status);
        return ServerResponse.success(ResponseCodeEnum.UPDATE_SUCCESS);
    }

    /**
     * 删除导航
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ServerResponse deleteNavigation(@PathVariable(value = "id") Integer id) {
        navigationService.delete(id);
        return ServerResponse.success(ResponseCodeEnum.DELETE_SUCCESS);
    }
}
