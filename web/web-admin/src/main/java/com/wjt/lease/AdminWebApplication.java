package com.wjt.lease;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName AdminWebApplication
 * @Description:
 * @Author 86178
 * @Date 2025/2/28 002818:46
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("com.wjt.lease.web.*.mapper")
public class AdminWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminWebApplication.class, args);
    }
}
