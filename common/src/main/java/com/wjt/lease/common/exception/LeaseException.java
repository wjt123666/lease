package com.wjt.lease.common.exception;

import com.wjt.lease.common.result.ResultCodeEnum;
import lombok.Data;

/**
 * @ClassName LeaseException
 * @Description:
 * @Author 86178
 * @Date 2025/3/5 000518:23
 * @Version 1.0
 */
@Data
public class LeaseException extends RuntimeException{

    // 异常状态码
    private Integer code;

    /**
     * 通过状态码和错误消息创建异常对象
     * @param message
     * @param code
     */
    public LeaseException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 根据响应结果枚举对象创建异常对象
     * @param resultCodeEnum
     */
    public LeaseException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }


}
