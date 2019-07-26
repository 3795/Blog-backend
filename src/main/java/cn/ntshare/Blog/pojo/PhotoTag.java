package cn.ntshare.Blog.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 图片Tag
 */
@Data
public class PhotoTag implements Serializable {

    private static final long serialVersionUID = -1783374755369816888L;

    private Integer id;

    private String name;

    private Integer status;

    private Date createTime;

    private Integer quantity;

    public PhotoTag(String name) {
        this.name = name;
    }

    public PhotoTag(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public PhotoTag(String name, Integer status) {
        this.name = name;
        this.status = status;
    }

    public PhotoTag(String name, Integer status, Integer quantity) {
        this.name = name;
        this.status = status;
        this.quantity = quantity;
    }
}
