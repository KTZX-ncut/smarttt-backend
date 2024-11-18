package com.example.smartttcommon.config;

import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lunSir
 * @create 2024-10-22 14:40
 * minio.endpoint=
 * minio.access-key=minioadmin
 * minio.secret-key=@Dwl1234567890
 */
@Configuration
@Slf4j
public class MinioConfig {

    // minio-server:9000
    @Value("${minio.url}")
    private String URL;

    @Value("${minio.accessKey}")
    private String ACCESSKEY;

    @Value("${minio.secretKey}")
    private String SECRETKEY;

    //private static final String URL = "http://157.0.19.2:10317";

    //private static final String ACCESSKEY = "minioadmin";

    //private static final String SECRETKEY = "@Dwl1234567890";

    @Bean
    MinioClient minioClient(){
        log.info("---> MinioConfig.minioClient is starting ....");
        return MinioClient.builder()
                .endpoint(URL)
                .credentials(ACCESSKEY,SECRETKEY)
                .build();
    }


}
