package cn.ntshare.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created By Q.Hao
 * Description: 公共状态信息枚举
 * Created At 2019/4/25
 */
@AllArgsConstructor
@Getter
public enum CommonStatusEnum {
    OFF(0, "关闭"),
    ON(1, "开启")
            ;

    Integer code;

    String msg;
}
