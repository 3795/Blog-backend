package cn.ntshare.Blog.controller.api.v1.backend;

import cn.ntshare.Blog.constant.SystemConstant;
import cn.ntshare.Blog.dto.UserDTO;
import cn.ntshare.Blog.enums.ResponseCodeEnum;
import cn.ntshare.Blog.form.UserForm;
import cn.ntshare.Blog.pojo.User;
import cn.ntshare.Blog.service.UserService;
import cn.ntshare.Blog.util.CookieUtil;
import cn.ntshare.Blog.util.RedisUtil;
import cn.ntshare.Blog.vo.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created By Seven.wk
 * Description: 用户控制器
 * Created At 2018/11/14
 */
@RestController
@RequestMapping("/backend/user")
@Slf4j
@Api(tags = "管理用户信息接口")
public class BUserControllerV1 {

    @Autowired
    private UserService userService;

    /**
     * 获取用户的简要信息
     * @param request
     * @return
     */
    @ApiOperation(value = "查询用户简要信息")
    @GetMapping("/brief")
    public ServerResponse getBriefInfo(HttpServletRequest request) {
        UserDTO user = userService.queryUserInfo(request);
        UserDTO userDTO = new UserDTO(user.getUsername(), user.getAvatar());
        return ServerResponse.success(userDTO);
    }

    /**
     * 获取用户的详细信息
     * @param request
     * @return
     */
    @GetMapping("/detail")
    @ApiOperation(value = "查询用户详细信息")
    public ServerResponse getDetailInfo(HttpServletRequest request) {
        UserDTO userDTO = userService.queryUserInfo(request);
        userDTO.setId(null);
        return ServerResponse.success(userDTO);
    }

    /**
     * 确认用户登录
     * @return
     */
    @GetMapping("isLogin")
    @ApiOperation(value = "查询用户登录状态")
    public ServerResponse isLogin() {
        return ServerResponse.success(true);
    }

    /**
     * 用户退出登录
     * @return
     */
    @GetMapping("/logout")
    @ApiOperation(value = "用户退出登录")
    public ServerResponse logout(HttpServletRequest request,
                                 HttpServletResponse response) {
        String loginToken = CookieUtil.readCookie(request, SystemConstant.LOGIN_TOKEN);
        CookieUtil.delCookie(request, response, SystemConstant.LOGIN_TOKEN);
        RedisUtil.del(loginToken);
        return ServerResponse.success(ResponseCodeEnum.LOGOUT_SUCCESS);
    }

    /**
     * 用户更新信息
     * @param request
     * @param userForm
     * @param result
     * @return
     */
    @PutMapping("/info")
    @ApiOperation(value = "更新用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "phone", value = "联系电话", required = true, paramType = "query"),
            @ApiImplicitParam(name = "email", value = "电子邮箱", required = true, paramType = "query"),
            @ApiImplicitParam(name = "signature", value = "个性签名", paramType = "query")
    })
    public ServerResponse updateInfo(HttpServletRequest request,
                                     @Valid UserForm userForm,
                                     BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.error(result.getFieldError().getDefaultMessage());
        }
        User user = new User();
        BeanUtils.copyProperties(userForm, user);
        userService.updateInfo(request, user);
        return ServerResponse.success(ResponseCodeEnum.UPDATE_SUCCESS);
    }

    /**
     * 更新用户头像
     * @param request
     * @param file
     * @return
     */
    @PostMapping("/avatar")
    @ApiOperation(value = "更新用户头像")
    public ServerResponse updateAvatar(HttpServletRequest request,
                                       @RequestParam("file") MultipartFile file) {
        String avatar = userService.updateAvatar(request, file);
        return ServerResponse.success(ResponseCodeEnum.UPDATE_SUCCESS, avatar);
    }

    /**
     * 更新用户密码
     * @param request
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @PatchMapping("/password")
    @ApiOperation(value = "更新用户密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oldPassword", value = "旧密码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "newPassword", value = "新密码", required = true, paramType = "query")
    })
    public ServerResponse updatePassword(HttpServletRequest request,
                                         @RequestParam("oldPassword") String oldPassword,
                                         @RequestParam("newPassword") String newPassword) {
        userService.updatePassword(request, oldPassword, newPassword);
        return ServerResponse.success(ResponseCodeEnum.UPDATE_SUCCESS);
    }
}
