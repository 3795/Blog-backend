package cn.ntshare.Blog.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * Created By Seven.wk
 * Description: 轮播图表单
 * Created At 2019/01/03
 */
@Getter
@Setter
@NoArgsConstructor
public class CarouselImgForm {

    @NotEmpty(message = "图片地址不能为空")
    private String img;

    private String link;

}
