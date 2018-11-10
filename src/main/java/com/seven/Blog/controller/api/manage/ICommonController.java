package com.seven.Blog.controller.api.manage;

import com.alibaba.fastjson.JSONObject;
import com.seven.Blog.enums.ResponseCodeEnum;
import com.seven.Blog.utils.FileUtil;
import com.seven.Blog.vo.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
     * editor编辑器上传图片
     * @param file
     * @return
     */
    @PostMapping("/editorUpload")
    public String editorUpload(@RequestParam(value = "editormd-image-file") MultipartFile file) {
        JSONObject jsonObject = new JSONObject();
        if (!file.isEmpty()) {
            try {
                String imgPath = fileUtil.uploadImg(file);
                jsonObject.put("success", 1);
                jsonObject.put("msg", "上传成功");
                jsonObject.put("url", imgPath);
            } catch (IOException e) {
                jsonObject.put("success", 1);
                jsonObject.put("msg", "IO异常");
                jsonObject.put("url", "");
            }
        } else {
            jsonObject.put("success", 0);
            jsonObject.put("msg", "上传失败，文件不能为空");
            jsonObject.put("url", "");
        }
        return jsonObject.toString();
    }

}
