package com.wjt.lease.common.minio;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MinioConfiguration
 * @Description: Minio配置类
 * @Author 86178
 * @Date 2025/3/4 000415:03
 * @Version 1.0
 */
@Configuration
//@EnableConfigurationProperties(MinioProperties.class)
@ConfigurationPropertiesScan("com.wjt.lease.common.minio")
public class MinioConfiguration {

    @Autowired
    private MinioProperties minioProperties;

    @Bean
    public MinioClient minioClient() {
        return MinioClient
                .builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
    }
}
