package cn.ntshare.blog.controller;

import cn.ntshare.blog.enums.ResponseCodeEnum;
import cn.ntshare.blog.service.FileService;
import cn.ntshare.blog.util.FileUtil;
import cn.ntshare.blog.vo.ServerResponse;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 上传图片
     * @param file
     * @return
     */
    @PostMapping("/img/upload")
    public ServerResponse upload(@RequestParam("file") MultipartFile file) {
        String imgPath = fileService.uploadImg(file);
        return ServerResponse.success(imgPath);
    }

    /**
     * Editor编辑器上传图片
     * @param file
     * @return
     */
    @PostMapping("/img/edUpload")
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
    @PostMapping("/img/delete")
    public ServerResponse deleteImg(@RequestParam("imgName") String imgName) {
        fileService.deleteImg(imgName);
        return ServerResponse.success(ResponseCodeEnum.FILE_DELETE_SUCCESS);
    }

    /**
     * 批量删除FTP服务器中的图片
     * @param imgList
     * @return
     */
    @PostMapping("/imgs/delete")
    public ServerResponse deleteImgs(@RequestBody List<String> imgList) {
        fileService.deleteImgs(imgList);
        return ServerResponse.success(ResponseCodeEnum.FILE_DELETE_SUCCESS);
    }
}
