package com.wjt.lease.web.admin.service;

import com.wjt.lease.common.result.Result;
import com.wjt.lease.model.entity.FeeValue;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
* @author liubo
* @description 针对表【fee_value(杂项费用值表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface FeeValueService extends IService<FeeValue> {


    /**
     * 保存或更新杂费值
     * @param feeValue 杂费值
     * @return Result
     */
    Result saveOrUpdateFeeValue(FeeValue feeValue);


    /**
     * 根据id删除杂费值
     * @param id 杂费值id
     * @return Result
     */
    Result deleteFeeValueById(Long id);
}
