package cn.ntshare.blog.pojo;

import lombok.Data;

/**
 * Created By Q.Hao
 * Description: 轮播图实体类
 * Created At 2019/4/25
 */
@Data
public class CarouselImg {

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
