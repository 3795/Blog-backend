package com.seven.Blog.controller.api.manage;

import com.seven.Blog.enums.ResponseCodeEnum;
import com.seven.Blog.response.ServerResponse;
import com.seven.Blog.utils.Const;
import com.seven.Blog.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created By Seven.wk
 * Description: 后台管理公用的api
 * Created At 2018/08/09
 */
@RestController
@RequestMapping("/api/manage")
public class ICommonController {

    @Autowired
    private FileUtil fileUtil;

    /**
     * 文件上传API
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public ServerResponse upload(@RequestParam("file") MultipartFile file) {
        if(!file.isEmpty()) {
            try {
                String imgPath = fileUtil.uploadImg(file);
                if(imgPath != null)
                    return ServerResponse.success(ResponseCodeEnum.FILE_UPLOAD_SUCCESS, imgPath);
                else
                    return ServerResponse.error("上传文件遇到错误，imgPath为NULL");
            } catch (IOException e) {
                return ServerResponse.error(ResponseCodeEnum.FILE_UPLOAD_FAILED.getMsg() + ": " + e.getMessage());
            }
        }
        return ServerResponse.error(ResponseCodeEnum.FILE_CANNOT_BE_EMPTY);
    }

    /**
     * 获得UEditor编辑器的配置
     * @return
     */
    @GetMapping("/getUeditorConfig")
    public String getUeditorConfig() {
        return Const.ueditorConfig();
    }

    /**
     * UEditor编辑器上传文件功能API
     * @param file
     * @return
     */
    @PostMapping("/ueditorUpload")
    public String ueditorUpload(@RequestParam("file") MultipartFile file) {
        String result = "";
        if(!file.isEmpty()) {
            try {
                String originalFileName = file.getOriginalFilename();
                String imgPath = fileUtil.uploadImg(file);
                result = "{\n" +
                        "    \"state\": \"SUCCESS\",\n" +
                        "    \"url\": \"" + imgPath + "\",\n" +
                        "    \"title\": \"" + originalFileName + "\",\n" +
                        "    \"original\": \"" + originalFileName + "\"\n" +
                        "}";
            } catch (IOException e) {
                result = e.getMessage();
            }
        }
        return result;
    }

}
