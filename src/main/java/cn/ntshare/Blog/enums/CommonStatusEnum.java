package cn.ntshare.Blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created By Seven.wk
 * Description: 公用的状态枚举
 * Created At 2018/11/21
 */
@Getter
@AllArgsConstructor
public enum CommonStatusEnum {

    OFF(0, "关闭"),
    ON(1, "开启")
    ;

    Integer code;

    String msg;

}
