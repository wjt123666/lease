package com.wjt.lease;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName AppWebApplication
 * @Description:
 * @Author 86178
 * @Date 2025/3/8 000814:15
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("com.wjt.lease.web.*.mapper") // 扫描mybatis的mapper接口
public class AppWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppWebApplication.class, args);
    }
}
