package com.wjt.lease.web.admin.custom.config;

import com.wjt.lease.web.admin.custom.converter.StringToBaseEnumConverterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName WebMvcConfiguration
 * @Description: 自定义WebMvcConfigurer
 * @Author 86178
 * @Date 2025/3/1 000118:01
 * @Version 1.0
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private StringToBaseEnumConverterFactory stringToBaseEnumConverterFactory;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(this.stringToBaseEnumConverterFactory);
    }
}
