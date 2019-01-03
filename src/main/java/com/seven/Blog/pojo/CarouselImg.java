package com.seven.Blog.pojo;

import lombok.*;

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
