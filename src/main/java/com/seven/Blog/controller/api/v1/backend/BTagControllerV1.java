package com.seven.Blog.controller.api.v1.backend;

import com.github.pagehelper.PageInfo;
import com.seven.Blog.dto.TagDTO;
import com.seven.Blog.enums.ResponseCodeEnum;
import com.seven.Blog.form.TagForm;
import com.seven.Blog.pojo.Tag;
import com.seven.Blog.service.TagService;
import com.seven.Blog.vo.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created By Seven.wk
 * Description: 标签管理器
 * Created At 2018/12/24
 */
@RestController
@RequestMapping("/backend/tag")
public class BTagControllerV1 {

    @Autowired
    private TagService tagService;

    /**
     * 查询所有的标签
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping
    public ServerResponse queryTags(@RequestParam(value = "status", required = false) Integer status,
                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        PageInfo pageInfo = tagService.queryTags(status, pageNum, pageSize);
        return ServerResponse.success(pageInfo);
    }

    /**
     * 查找启用的tag选项
     * @return
     */
    @GetMapping("/options")
    public ServerResponse queryTagOptions() {
        List<TagDTO> list = tagService.queryTagOptions();
        return ServerResponse.success(list);
    }

    /**
     * 根据id查找tag
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ServerResponse queryTagById(@PathVariable("id") Integer id) {
        return ServerResponse.success(tagService.queryTagById(id));
    }

    /**
     * 新建标签
     * @param tagForm
     * @param result
     * @return
     */
    @PostMapping
    public ServerResponse createTag(@Valid TagForm tagForm,
                                    BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.error(result.getFieldError().getDefaultMessage());
        }

        Tag tag = new Tag(tagForm.getName(), tagForm.getColor());
        tagService.insertTag(tag);

        return ServerResponse.success(ResponseCodeEnum.INSERT_SUCCESS);
    }

    /**
     * 更新一个标签
     * @param tagForm
     * @param result
     * @return
     */
    @PutMapping("/{id}")
    public ServerResponse updateTag(@Valid TagForm tagForm,
                                    @PathVariable("id") Integer id,
                                    BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.error(result.getFieldError().getDefaultMessage());
        }

        Tag tag = new Tag(id, tagForm.getName(), tagForm.getColor());
        tagService.updateTag(tag);

        return ServerResponse.success(ResponseCodeEnum.UPDATE_SUCCESS);
    }

    /**
     * 更新标签的状态
     * @param id
     * @return
     */
    @PatchMapping("/{id}")
    public ServerResponse updateTagStatus(@PathVariable("id") Integer id) {
        tagService.updateStatus(id);
        return ServerResponse.success(ResponseCodeEnum.UPDATE_SUCCESS);
    }

    /**
     * 删除标签
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ServerResponse deleteTag(@PathVariable("id") Integer id) {
        tagService.deleteTag(id);
        return ServerResponse.success(ResponseCodeEnum.DELETE_SUCCESS);
    }

}
