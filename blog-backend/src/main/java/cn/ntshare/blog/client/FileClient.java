package cn.ntshare.blog.client;

import cn.ntshare.blog.vo.ServerResponse;
import feign.Logger;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;


@FeignClient(value = "blog-file", configuration = FileClient.FeignMultipartSupportConfig.class)
public interface FileClient {

    @PostMapping(value = "/img/delete")
    ServerResponse deleteImg(@RequestParam("imgName") String imgName);

    @PostMapping(value = "/img/upload", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ServerResponse upload(@RequestPart("file") MultipartFile file);

    class FeignMultipartSupportConfig {

        @Bean
        public Encoder multipartFormEncoder() {
            return new SpringFormEncoder();
        }

        @Bean
        public feign.Logger.Level multipartLoggerLevel() {
            return Logger.Level.FULL;
        }
    }

}
