package cn.ntshare.blog.util;

import cn.ntshare.blog.constant.SystemConstant;
import cn.ntshare.blog.dto.BaiduLinkSubmissionDTO;
import cn.ntshare.blog.enums.ResponseCodeEnum;
import cn.ntshare.blog.exception.SystemException;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 发送Http请求
 */
public class HttpUtil {
    /**
     * 向百度搜索提交站点数据
     * @param links
     */
    public static BaiduLinkSubmissionDTO baiduLinkSubmission(List<String> links) {

        // 组合urls
        StringBuilder indexLinks = new StringBuilder();
        for (String link : links) {
            indexLinks.append(link).append("\n");
        }

        String url = SystemConstant.BAIDU_LINK_SUBMISSION_URL;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpMethod httpMethod = HttpMethod.POST;

        // 以Text/Html方式提交数据
        httpHeaders.setContentType(MediaType.TEXT_HTML);
        // 合成请求
        HttpEntity<String> requestEntity = new HttpEntity<>(indexLinks.toString(), httpHeaders);
        // 发送Http请求
        ResponseEntity<BaiduLinkSubmissionDTO> response = restTemplate.exchange(url, httpMethod, requestEntity, BaiduLinkSubmissionDTO.class);
        // 判断结果
        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            throw new SystemException(ResponseCodeEnum.HTTP_REQUEST_FAILED);
        }

        return response.getBody();
    }
}
