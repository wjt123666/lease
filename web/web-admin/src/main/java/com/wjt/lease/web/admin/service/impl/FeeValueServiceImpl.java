package com.wjt.lease.web.admin.service.impl;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjt.lease.common.result.Result;
import com.wjt.lease.common.result.ResultCodeEnum;
import com.wjt.lease.model.entity.FeeValue;
import com.wjt.lease.web.admin.service.FeeValueService;
import com.wjt.lease.web.admin.mapper.FeeValueMapper;
import org.springframework.stereotype.Service;

/**
* @author liubo
* @description 针对表【fee_value(杂项费用值表)】的数据库操作Service实现
* @createDate 2023-07-24 15:48:00
*/
@Service
public class FeeValueServiceImpl extends ServiceImpl<FeeValueMapper, FeeValue> implements FeeValueService{

    /**
     * 保存或更新杂费值
     * @param feeValue 杂费值
     * @return Result
     */
    @Override
    public Result saveOrUpdateFeeValue(FeeValue feeValue) {
        if (ObjUtil.isNull(feeValue)) return Result.build(null, ResultCodeEnum.PARAM_ERROR);
        return saveOrUpdate(feeValue) ? Result.ok() : Result.fail();
    }

    /**
     * 根据id删除杂费值
     * @param id 杂费值id
     * @return Result
     */
    @Override
    public Result deleteFeeValueById(Long id) {
        if (ObjUtil.isNull(id)) return Result.build(null, ResultCodeEnum.PARAM_ERROR);
        return removeById(id) ? Result.ok() : Result.fail();
    }
}




