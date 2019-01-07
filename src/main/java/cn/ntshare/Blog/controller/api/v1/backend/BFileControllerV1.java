package cn.ntshare.Blog.controller.api.v1.backend;

import cn.ntshare.Blog.enums.ResponseCodeEnum;
import cn.ntshare.Blog.service.FileService;
import cn.ntshare.Blog.util.FileUtil;
import cn.ntshare.Blog.vo.ServerResponse;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created By Seven.wk
 * Description: 文件管理
 * Created At 2018/11/26
 */
@RequestMapping("/backend")
@RestController
@Api(tags = "文件管理接口")
public class BFileControllerV1 {

    @Autowired
    private FileService fileService;

    /**
     * 上传图片
     * @param file
     * @return
     */
    @PostMapping("/uploadImg")
    @ApiOperation("文件上传接口")
    public ServerResponse upload(@RequestParam("file") MultipartFile file) {
        String imgPath = fileService.uploadImg(file);
        return ServerResponse.success(imgPath);
    }

    /**
     * Editor编辑器上传图片
     * @param file
     * @return
     */
    @PostMapping("/edUploadImg")
    @ApiOperation("Editor编辑器图片上传接口")
    public String editorUpload(@RequestParam(value = "editormd-image-file") MultipartFile file) {
        JSONObject jsonObject = new JSONObject();
        if (!file.isEmpty()) {
            try {
                String imgPath = FileUtil.uploadImg(file);
                jsonObject.put("success", 1);
                jsonObject.put("msg", ResponseCodeEnum.FILE_UPLOAD_SUCCESS.getMsg());
                jsonObject.put("url", imgPath);
            } catch (Exception e) {
                jsonObject.put("success", 0);
                jsonObject.put("msg", e.getMessage());
                jsonObject.put("url", "");
            }
        } else {
            jsonObject.put("success", 0);
            jsonObject.put("msg", ResponseCodeEnum.FILE_CANNOT_BE_EMPTY.getMsg());
            jsonObject.put("url", "");
        }
        return jsonObject.toString();
    }

    /**
     * 删除FTP服务器中的图片
     * @param imgName
     * @return
     */
    @PostMapping("/deleteImg")
    @ApiOperation("删除图片接口")
    @ApiImplicitParam(name = "imgName", value = "图片名称", required = true, paramType = "query")
    public ServerResponse deleteImg(@RequestParam("imgName") String imgName) {
        fileService.deleteImg(imgName);
        return ServerResponse.success(ResponseCodeEnum.FILE_DELETE_SUCCESS);
    }
}
