package cn.ntshare.Blog.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created By Seven.wk
 * Description: 图片上传记录实体
 * Created At 2019/01/07
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ImgRecord {

    private Integer id;

    private String img;

    private Integer articleId = 0;

    private Integer carouselImgId = 0;

    private Integer userId = 0;

    public ImgRecord(String img) {
        this.img = img;
    }

}
