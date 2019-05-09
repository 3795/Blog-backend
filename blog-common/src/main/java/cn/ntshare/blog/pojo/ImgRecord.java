package cn.ntshare.blog.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created By Q.Hao
 * Description: 图片上传记录
 * Created At 2019/4/25
 */
@NoArgsConstructor
@Getter
@Setter
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
