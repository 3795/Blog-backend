package cn.ntshare.Blog.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PhotoTagDTO {

    private Integer id;

    private String name;

    private Integer quantity;

    private Integer status;

    private Date createTime;
}
