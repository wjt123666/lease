package com.wjt.lease;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ClassName AdminWebApplication
 * @Description:
 * @Author 86178
 * @Date 2025/2/28 002818:46
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("com.wjt.lease.web.*.mapper") // 扫描mybatis的mapper接口
@EnableScheduling // 开启定时任务
public class AdminWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminWebApplication.class, args);
    }
}
