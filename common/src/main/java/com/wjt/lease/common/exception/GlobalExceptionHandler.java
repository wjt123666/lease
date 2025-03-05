package com.wjt.lease.common.exception;

import com.wjt.lease.common.result.Result;
import com.wjt.lease.common.result.ResultCodeEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName GlobalExceptionHandler
 * @Description: 全局异常处理器
 * @Author 86178
 * @Date 2025/3/4 000416:58
 * @Version 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        e.printStackTrace();
        return Result.fail();
    }

    @ExceptionHandler(LeaseException.class)
    public Result handleException(LeaseException e) {
        e.printStackTrace();
        return Result.fail(e.getCode(), e.getMessage());
    }
}
