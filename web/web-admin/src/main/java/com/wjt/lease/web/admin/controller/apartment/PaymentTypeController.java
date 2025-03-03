package com.wjt.lease.web.admin.controller.apartment;


import com.wjt.lease.common.result.Result;
import com.wjt.lease.model.entity.PaymentType;
import com.wjt.lease.web.admin.service.PaymentTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "支付方式管理")
@RequestMapping("/admin/payment")
@RestController
public class PaymentTypeController {

    @Autowired
    private PaymentTypeService paymentTypeService;

    @Operation(summary = "查询全部支付方式列表")
    @GetMapping("list")
    public Result<List<PaymentType>> listPaymentType() {
        return paymentTypeService.listPaymentType();
    }

    @Operation(summary = "保存或更新支付方式")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdatePaymentType(@RequestBody PaymentType paymentType) {
        return paymentTypeService.saveOrUpdatePaymentType(paymentType);
    }

    @Operation(summary = "根据ID删除支付方式")
    @DeleteMapping("deleteById")
    public Result deletePaymentById(@RequestParam Long id) {
        return paymentTypeService.deletePaymentById(id);
    }

}















