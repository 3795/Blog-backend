package cn.ntshare.Blog.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 图片墙
 */
@Data
public class Photo implements Serializable {

    private static final long serialVersionUID = 6944361900224976598L;

    private Integer id;

    private String title;

    private String img;

    private Integer photoTagId;

    private Integer status;

    private Date createTime;
}
