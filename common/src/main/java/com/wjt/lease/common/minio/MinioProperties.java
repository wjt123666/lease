package com.wjt.lease.common.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName MinioProperties
 * @Description: Minio配置类
 * @Author 86178
 * @Date 2025/3/4 000415:02
 * @Version 1.0
 */
@Data
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {

    private String endpoint;

    private String accessKey;

    private String secretKey;

    private String bucketName;
}
