package com.seven.Blog.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Created By Seven.wk
 * Description: 文章表单
 * Created At 2018/08/09
 */
@Getter
@Setter
@NoArgsConstructor
public class ArticleForm {

    @NotEmpty(message = "标题不能为空")
    private String title;

    @NotEmpty(message = "图片路径不能为空")
    private String img;

    @NotEmpty(message = "文章摘要不能为空")
    private String summary;

    @NotEmpty(message = "文章内容不能为空")
    private String content;

    @NotEmpty(message = "文章分类不能为空")
    private String categoryId;

    @NotEmpty(message = "文章类型不能为空")
    private String type;

    @NotEmpty(message = "文章标签不能为空")
    private List<Integer> tags;

}
