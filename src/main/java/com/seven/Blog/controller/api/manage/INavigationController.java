package com.seven.Blog.controller.api.manage;

import com.seven.Blog.form.NavigationForm;
import com.seven.Blog.pojo.Navigation;
import com.seven.Blog.vo.ServerResponse;
import com.seven.Blog.service.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created By Seven.wk
 * Description: 导航管理的API
 * Created At 2018/08/10
 */
@RestController
@RequestMapping("/api/manage/navigation")
public class INavigationController {

    @Autowired
    private NavigationService navigationService;

    /**
     * 添加一个导航信息
     * @param navigationForm
     * @param result
     * @return
     */
    @PostMapping("")
    public ServerResponse addNavigation(@Valid NavigationForm navigationForm,
                                        BindingResult result) {
        if(result.hasErrors())
            return ServerResponse.error(result.getFieldError().getDefaultMessage());
        int priority = Integer.parseInt(navigationForm.getPriority());
        int status = Integer.parseInt(navigationForm.getStatus());
        return navigationService.addNavigation(navigationForm.getName(), priority, navigationForm.getLink(), status);
    }

    /**
     * 更改某个导航的状态信息
     * @param id
     * @return
     */
    @PatchMapping("/{id}")
    public ServerResponse changeNavigationStatus(@PathVariable("id") Integer id) {
        return navigationService.changeCategoryStatus(id);
    }

    /**
     * 更新一个导航的信息
     * @param navigationForm
     * @param result
     * @return
     */
    @PutMapping("")
    public ServerResponse updateNavigation(@Valid NavigationForm navigationForm,
                                           BindingResult result) {
        if(result.hasErrors())
            return ServerResponse.error(result.getFieldError().getDefaultMessage());
//        int id = Integer.parseInt(navigationForm.getId());
        int id = -1;
        int priority = Integer.parseInt(navigationForm.getPriority());
        int status = Integer.parseInt(navigationForm.getStatus());
        Navigation navigation = new Navigation(id, navigationForm.getName(), priority,
                navigationForm.getLink(), status);
        return navigationService.updateNavigation(navigation);
    }

    /**
     * 删除一个导航
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ServerResponse deleteNavigation(@PathVariable("id") Integer id) {
        return navigationService.deleteNavigation(id);
    }
}
