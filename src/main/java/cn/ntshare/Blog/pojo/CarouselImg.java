package cn.ntshare.Blog.pojo;

import lombok.*;

import java.io.Serializable;

/**
 * Created By Seven.wk
 * Description: 轮播图片实体
 * Created At 2019/01/03
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CarouselImg implements Serializable {

    private static final long serialVersionUID = 8880871865468945038L;

    private Integer id;

    private String img;

    private String link;

    private Integer status = 1;

    public CarouselImg(String img, String link) {
        this.img = img;
        this.link = link;
    }

    public CarouselImg(String img, String link, Integer status) {
        this.img = img;
        this.link = link;
        this.status = status;
    }
}
