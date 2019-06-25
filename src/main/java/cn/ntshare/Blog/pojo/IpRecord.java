package cn.ntshare.Blog.pojo;

import cn.ntshare.Blog.enums.CommonStatusEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Created By Seven.wk
 * Description: IP记录实体
 * Created At 2019/01/11
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class IpRecord implements Serializable {

    private static final long serialVersionUID = -280055351351799699L;

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
