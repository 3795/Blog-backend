package cn.ntshare.Blog.dto;

import java.util.Arrays;

/**
 * Created By Seven.wk
 * Description: 向百度搜索提交链接后返回的结果
 * Created At 2019/02/27
 */
public class BaiduLinkSubmissionDTO {

    // 成功推送的url条数
    private Integer success;

    // 当天剩余的可推送url条数
    private Integer remain;

    // 由于不是本站url而未处理的url列表
    private String[] notSameSite;

    // 不合法的url列表
    private String[] notValid;

    public BaiduLinkSubmissionDTO() {
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Integer getRemain() {
        return remain;
    }

    public void setRemain(Integer remain) {
        this.remain = remain;
    }

    public String[] getNotSameSite() {
        return notSameSite;
    }

    public void setNotSameSite(String[] notSameSite) {
        this.notSameSite = notSameSite;
    }

    public String[] getNotValid() {
        return notValid;
    }

    public void setNotValid(String[] notValid) {
        this.notValid = notValid;
    }

    @Override
    public String toString() {
        return "BaiduLinkSubmissionDTO{" +
                "success=" + success +
                ", remain=" + remain +
                ", notSameSite=" + Arrays.toString(notSameSite) +
                ", notValid=" + Arrays.toString(notValid) +
                '}';
    }
}
