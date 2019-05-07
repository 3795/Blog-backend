package cn.ntshare.blog.dto;

import lombok.Data;

/**
 * Created By Q.Hao
 * Description: 向百度搜索提交链接后返回的结果
 * Created At 2019/5/7
 */
@Data
public class BaiduLinkSubmissionDTO {

    // 成功推送的url条数
    private Integer success;

    // 当天剩余的可推送url条数
    private Integer remain;

    // 由于不是本站url而未处理的url列表
    private String[] notSameSite;

    // 不合法的url列表
    private String[] notValid;
}
