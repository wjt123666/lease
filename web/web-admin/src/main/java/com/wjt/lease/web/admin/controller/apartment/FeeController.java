package com.wjt.lease.web.admin.controller.apartment;


import com.wjt.lease.common.result.Result;
import com.wjt.lease.model.entity.FeeKey;
import com.wjt.lease.model.entity.FeeValue;
import com.wjt.lease.web.admin.service.FeeKeyService;
import com.wjt.lease.web.admin.service.FeeValueService;
import com.wjt.lease.web.admin.vo.fee.FeeKeyVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "房间杂费管理")
@RestController
@RequestMapping("/admin/fee")
public class FeeController {

    @Autowired
    private FeeKeyService feeKeyService;

    @Autowired
    private FeeValueService feeValueService;

    @Operation(summary = "保存或更新杂费名称")
    @PostMapping("key/saveOrUpdate")
    public Result saveOrUpdateFeeKey(@RequestBody FeeKey feeKey) {
        return feeKeyService.saveOrUpdateFeeKey(feeKey);
    }

    @Operation(summary = "保存或更新杂费值")
    @PostMapping("value/saveOrUpdate")
    public Result saveOrUpdateFeeValue(@RequestBody FeeValue feeValue) {
        return feeValueService.saveOrUpdateFeeValue(feeValue);
    }


    @Operation(summary = "查询全部杂费名称和杂费值列表")
    @GetMapping("list")
    public Result<List<FeeKeyVo>> feeInfoList() {
        return feeKeyService.feeInfoList();
    }

    @Operation(summary = "根据id删除杂费名称")
    @DeleteMapping("key/deleteById")
    public Result deleteFeeKeyById(@RequestParam Long feeKeyId) {
        return feeKeyService.deleteFeeKeyById(feeKeyId);
    }

    @Operation(summary = "根据id删除杂费值")
    @DeleteMapping("value/deleteById")
    public Result deleteFeeValueById(@RequestParam Long id) {
        return feeValueService.deleteFeeValueById(id);
    }
}
