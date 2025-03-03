package com.wjt.lease.web.admin.custom.converter;

import com.wjt.lease.model.enums.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName StringToBaseEnumConverterFactory
 * @Description: 自定义转换器工厂，用于将String类型转换为BaseEnum类型
 * @Author 86178
 * @Date 2025/3/3 000315:00
 * @Version 1.0
 */
@Component
public class StringToBaseEnumConverterFactory implements ConverterFactory<String, BaseEnum> {
    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return code -> {
            T[] constants = targetType.getEnumConstants();
            for (T constant : constants) {
                if (constant.getCode().equals(Integer.valueOf(code))) {
                    return constant;
                }
            }
            throw new IllegalArgumentException("Invalid item type code: " + code);
        };
    }
}
