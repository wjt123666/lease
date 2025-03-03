package com.wjt.lease.web.admin.service;

import com.wjt.lease.common.result.Result;
import com.wjt.lease.model.entity.PaymentType;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @author liubo
* @description 针对表【payment_type(支付方式表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface PaymentTypeService extends IService<PaymentType> {

    /**
     * 查询全部支付方式列表
     * @return Result<List<PaymentType>>
     */
     Result<List<PaymentType>> listPaymentType();


    /**
     * 保存或更新支付方式
     * @param paymentType 支付方式实体
     * @return Result
     */
    Result saveOrUpdatePaymentType(PaymentType paymentType);



    /**
     * 根据ID删除支付方式
     * @param id 支付方式ID
     * @return Result
     */
    Result deletePaymentById(Long id);
}
