package cn.ntshare.Blog.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created By Seven.wk
 * Description: 图片上传记录实体
 * Created At 2019/01/07
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ImgRecord implements Serializable {

    private static final long serialVersionUID = -6081861999506770425L;

    private Integer id;

    private String img;

    private Integer articleId = 0;

    private Integer carouselImgId = 0;

    private Integer userId = 0;

    public ImgRecord(String img) {
        this.img = img;
    }

}
