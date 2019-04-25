package cn.ntshare.blog.pojo;

import cn.ntshare.blog.enums.CommonStatusEnum;

import java.util.Date;

/**
 * Created By Q.Hao
 * Description: IP记录
 * Created At 2019/4/25
 */
public class IpRecord {

    private Integer id;

    private String ip;

    private Date lastTime = new Date();

    // 访问次数
    private Integer count = 1;

    // 该IP是否允许访问
    private Integer status = CommonStatusEnum.ON.getCode();

    public IpRecord(String ip) {
        this.ip = ip;
    }
}
