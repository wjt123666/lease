package com.wjt.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjt.lease.common.result.Result;
import com.wjt.lease.model.entity.PaymentType;
import com.wjt.lease.web.admin.service.PaymentTypeService;
import com.wjt.lease.web.admin.mapper.PaymentTypeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liubo
 * @description 针对表【payment_type(支付方式表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class PaymentTypeServiceImpl extends ServiceImpl<PaymentTypeMapper, PaymentType> implements PaymentTypeService {

    /**
     * 查询全部支付方式列表
     * @return Result<List < PaymentType>>
     */
    @Override
    public Result<List<PaymentType>> listPaymentType() {
        return Result.ok(list());
    }

    /**
     * 保存或更新支付方式
     * @param paymentType 支付方式实体
     * @return Result
     */
    @Override
    public Result saveOrUpdatePaymentType(PaymentType paymentType) {
        return saveOrUpdate(paymentType) ? Result.ok() : Result.fail();
    }

    /**
     * 根据ID删除支付方式
     * @param id 支付方式ID
     * @return Result
     */
    @Override
    public Result deletePaymentById(Long id) {
        return removeById(id) ? Result.ok() : Result.fail();
    }
}




