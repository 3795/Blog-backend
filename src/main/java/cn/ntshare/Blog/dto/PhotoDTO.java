package cn.ntshare.Blog.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PhotoDTO {

    private Integer id;

    private String title;

    private String img;

    private Integer photoTagId;

    private Integer status;

    private Date createTime;
}
