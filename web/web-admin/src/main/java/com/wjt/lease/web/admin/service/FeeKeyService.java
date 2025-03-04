package com.wjt.lease.web.admin.service;

import com.wjt.lease.common.result.Result;
import com.wjt.lease.model.entity.FeeKey;
import com.wjt.lease.model.entity.FeeValue;
import com.wjt.lease.web.admin.vo.fee.FeeKeyVo;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @author liubo
* @description 针对表【fee_key(杂项费用名称表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface FeeKeyService extends IService<FeeKey> {

    /**
     * 保存或更新杂费名称
     * @param feeKey 杂费名称
     * @return Result
     */
    Result saveOrUpdateFeeKey(FeeKey feeKey);


    /**
     * 查询全部杂费名称和杂费值列表
     * @return Result<List<FeeKeyVo>>
     */
    Result<List<FeeKeyVo>> feeInfoList();

    /**
     * 根据id删除杂费名称
     * @param feeKeyId 杂费名称id
     * @return Result
     */
    Result deleteFeeKeyById(Long feeKeyId);
}
